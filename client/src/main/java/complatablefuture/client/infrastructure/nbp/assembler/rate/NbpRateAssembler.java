package complatablefuture.client.infrastructure.nbp.assembler.rate;

import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.infrastructure.nbp.dto.ExchangeRatesSeries;

public interface NbpRateAssembler {

    Rate toRate(ExchangeRatesSeries dto);
}
