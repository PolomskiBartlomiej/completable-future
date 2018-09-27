package complatablefuture.client.infrastructure.nbp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Value
public class ExchangeRatesSeries implements RateInfo {
    @JsonProperty
    String code;
    @JsonProperty
    @Getter(AccessLevel.NONE)
    List<RateDto> rates;

    @Override
    public String code() {
        return code;
    }

    @Override
    public Double rate() {
       return rates.get(0).getMid();
    }
}
