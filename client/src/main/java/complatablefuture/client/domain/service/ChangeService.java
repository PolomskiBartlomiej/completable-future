package complatablefuture.client.domain.service;

import complatablefuture.client.domain.excpetion.RateException;
import complatablefuture.client.domain.model.Change;
import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.domain.port.BankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static complatablefuture.client.infrastructure.future.FutureUtil.tryJoinOrThrow;
import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ChangeService {

    private final BankApi bankApi;
    private final ChangeCalculator changeCalculator;

    public List<Change> change(Integer amount, List<String> currencyCodes) {

        List<CompletableFuture<Change>> changes = currencyCodes.stream()
                .map(bankApi::rate)
                .map(thenCalculateChange(amount))
                .collect(toList());

        CompletableFuture<Void> allOfFuture = allOf(changes.toArray(new CompletableFuture[0]));

        tryJoinOrThrow(allOfFuture, RateException::new);

        return changes.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }

    private Function<CompletableFuture<Rate>, CompletableFuture<Change>>
    thenCalculateChange(Integer amount) {
        return futureRate -> futureRate.thenApply(calculateChange(amount));
    }


    private Function<Rate, Change>
    calculateChange(Integer amount) {
        return rate -> changeCalculator.calculate(rate, amount);
    }

}
