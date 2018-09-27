package complatablefuture.client.infrastructure.nbp.adapter;

import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.domain.port.BankApi;
import complatablefuture.client.infrastructure.nbp.assembler.rate.NbpRateAssemblerFactory;
import complatablefuture.client.infrastructure.nbp.dto.ExchangeRatesSeries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Component
@RequiredArgsConstructor
class NBPBankApi implements BankApi {

    private final NbpURIPolicy nbpURIPolicy;
    private final NbpRateAssemblerFactory assemblerFactory;

    @Override
    public CompletableFuture<Rate> rate(String codeCurrency) {

        String currencyURL = nbpURIPolicy.rateUrlFrom(codeCurrency);

        return supplyAsync(getRate(currencyURL))
                .thenApply(dto -> assemblerFactory.of(dto).toRate(dto));
    }

    private Supplier<ExchangeRatesSeries> getRate(String url) {
        final RestTemplate template = new RestTemplate();
        sleepThread();
        return () -> template.getForObject(url, ExchangeRatesSeries.class);
    }

    private void sleepThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
