package complatablefuture.client.infrastructure.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
class RateDto {
    @JsonProperty
    Double bid;
    @JsonProperty
    Double ask;
    @JsonProperty
    Double mid;
}
