package complatablefuture.client.domain.service;

import complatablefuture.client.domain.excpetion.RateException;
import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.domain.port.BankApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static complatablefuture.client.infrastructure.future.FutureUtil.tryJoinOrThrow;
import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final BankApi bank;

    public List<Rate> actualRate(List<String> currencies) {

        List<CompletableFuture<Rate>> futures = currencies.stream()
                .map(bank::rate)
                .collect(toList());

        CompletableFuture<Void> allOfFuture = allOf(futures.toArray(new CompletableFuture[0]));

        tryJoinOrThrow(allOfFuture , RateException::new);

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());

    }

    public List<Rate> todayRate(List<String> currencies) {

        List<CompletableFuture<Rate>> futures = currencies.stream()
                .map(bank::rate)
                .map(future -> future.handle(
                        (rate, ex) -> {
                            if (ex == null) {
                                return  rate;
                            }

                            if (ex instanceof HttpClientErrorException) {
                                return null;
                            }

                            throw new RuntimeException();
                        }
                ))
                .collect(toList());

        CompletableFuture<Void> allOfFuture = allOf(futures.toArray(new CompletableFuture[0]));

        tryJoinOrThrow(allOfFuture , RateException::new);

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());
    }
}
