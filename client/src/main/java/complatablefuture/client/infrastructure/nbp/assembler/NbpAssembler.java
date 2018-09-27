package complatablefuture.client.infrastructure.nbp.assembler;

import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.infrastructure.nbp.dto.RateInfo;
import org.springframework.stereotype.Component;

@Component
public class NbpAssembler {

    public Rate toRate(RateInfo dto) {
        return Rate.builder()
                .code(dto.code())
                .rate(dto.rate())
                .build();
    }
}
