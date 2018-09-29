package complatablefuture.client.domain.service;

import complatablefuture.client.domain.excpetion.RateException;
import complatablefuture.client.domain.model.Change;
import complatablefuture.client.domain.port.BankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static complatablefuture.client.infrastructure.future.FutureUtil.tryJoinOrThrow;
import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ChangeService {

    private final BankApi bankApi;
    private final ChangeCalculator changeCalculator;

    public List<Change> change(BigDecimal amount, List<String> currencyCodes) {

        Stream<CompletableFuture<Change>> changes = currencyCodes.stream()
                .map(code -> bankApi.rate(code)
                    .thenApply(rate -> changeCalculator.calculate(rate, amount)));


        CompletableFuture<Void> allOfFuture = allOf(changes.toArray(CompletableFuture[]::new));

        tryJoinOrThrow(allOfFuture, RateException::new);

        return changes
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
