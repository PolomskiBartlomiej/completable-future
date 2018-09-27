package complatablefuture.client.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Rate {
    String code;
    Double rate;
}
