package complatablefuture.client.infrastructure.nbp.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
class NbpURIPolicy {

    private static final String SINGLE_RATE_URL = "http://api.nbp.pl/api/exchangerates/rates/{table}/{currency}?format=json";

    private final NbpTableResolver tableResolver;

    String rateUrlFrom(String currency) {

        return UriComponentsBuilder
                .fromHttpUrl(SINGLE_RATE_URL)
                .buildAndExpand(tableResolver.resolve(currency), currency)
                .toString();
    }
}
