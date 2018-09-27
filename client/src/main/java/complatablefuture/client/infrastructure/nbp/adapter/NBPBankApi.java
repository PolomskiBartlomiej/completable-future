package complatablefuture.client.infrastructure.nbp.adapter;

import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.domain.port.BankApi;
import complatablefuture.client.infrastructure.nbp.assembler.NbpAssembler;
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
    private final NbpAssembler assembler;

    @Override
    public CompletableFuture<Rate> rate(String codeCurrency) {

        String currencyURL = nbpURIPolicy.rateUrlFrom(codeCurrency);

        return supplyAsync(getRate(currencyURL))
                .thenApply(assembler::toRate);

    }

    private Supplier<ExchangeRatesSeries> getRate(String url) {
        final RestTemplate template = new RestTemplate();
        return () -> template.getForObject(url, ExchangeRatesSeries.class);
    }
}
