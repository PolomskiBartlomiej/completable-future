package complatablefuture.client.infrastructure.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class RateDto {
    @JsonProperty
    BigDecimal bid;
    @JsonProperty
    BigDecimal ask;
    @JsonProperty
    BigDecimal mid;
}
