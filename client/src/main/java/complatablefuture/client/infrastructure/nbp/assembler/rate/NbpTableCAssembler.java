package complatablefuture.client.infrastructure.nbp.assembler.rate;

import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.infrastructure.nbp.dto.ExchangeRatesSeries;

class NbpTableCAssembler implements NbpRateAssembler {

    @Override
    public Rate toRate(ExchangeRatesSeries dto) {
        return Rate.builder()
                .code(dto.getCode())
                .rate(dto.rates().get(0).getAsk())
                .build();
    }
}
