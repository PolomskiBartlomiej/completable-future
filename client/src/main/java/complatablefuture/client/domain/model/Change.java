package complatablefuture.client.domain.model;

import lombok.Value;

@Value
public class Change {
    String currencyCode;
    Double change;
}
