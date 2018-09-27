package complatablefuture.client.infrastructure.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.LinkedList;
import java.util.List;

@Value
public class ExchangeRatesSeries {
    @JsonProperty
    String code;

    @JsonProperty
    @Getter(AccessLevel.NONE)
    List<RateDto> rates;

    public List<RateDto> rates() {
        return new LinkedList<>((rates));
    }

}
