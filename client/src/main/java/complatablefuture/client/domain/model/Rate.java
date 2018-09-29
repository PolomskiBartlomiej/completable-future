package complatablefuture.client.domain.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class Rate {
    String code;
    BigDecimal rate;
}
