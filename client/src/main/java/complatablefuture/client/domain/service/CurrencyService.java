package complatablefuture.client.domain.service;

import complatablefuture.client.domain.excpetion.RateException;
import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.domain.port.BankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.stream.Stream;

import static complatablefuture.client.infrastructure.future.FutureUtil.tryJoinOrThrow;
import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final BankApi bank;

    public List<Rate> actualRate(List<String> currencies) {

        Stream<CompletableFuture<Rate>> futures = currencies.stream()
                                                            .map(bank::rate);

        CompletableFuture<Void> allOfFuture = allOf(futures.toArray(CompletableFuture[]::new));

        tryJoinOrThrow(allOfFuture , RateException::new);

        return futures
                .map(CompletableFuture::join)
                .collect(toList());

    }

    public List<Rate> todayRate(List<String> currencies) {

        Stream<CompletableFuture<Rate>> futures = currencies.stream()
                .map(bank::rate)
                .map(this::handleClientError);

        CompletableFuture<Void> allOfFuture = allOf(futures.toArray(CompletableFuture[]::new));

        tryJoinOrThrow(allOfFuture , RateException::new);

        return futures
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(toList());
    }

    private CompletableFuture<Rate> handleClientError(CompletableFuture<Rate> future) {
        return future.exceptionally(ex -> {
            if (ex.getCause() instanceof HttpClientErrorException ) return null;
            throw (CompletionException) ex;
        });
    }
}
