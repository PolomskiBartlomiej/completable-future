package complatablefuture.client.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Value
@Builder
public class Rate {
    String code;
    BigDecimal rate;
}
