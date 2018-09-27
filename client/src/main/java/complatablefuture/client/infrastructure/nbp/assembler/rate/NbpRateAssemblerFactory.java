package complatablefuture.client.infrastructure.nbp.assembler.rate;

import complatablefuture.client.infrastructure.nbp.adapter.NbpTableResolver;
import complatablefuture.client.infrastructure.nbp.dto.ExchangeRatesSeries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NbpRateAssemblerFactory {

    private static final String TABLE_C = "c";

    private final NbpTableResolver resolver;

    public NbpRateAssembler of(ExchangeRatesSeries ratesSeries) {

        if(resolver.resolve(ratesSeries.getCode().toLowerCase()).equals(TABLE_C)) {
            return new NbpTableCAssembler();
        }

        return new NbpTableABAssembler();
    }
}
