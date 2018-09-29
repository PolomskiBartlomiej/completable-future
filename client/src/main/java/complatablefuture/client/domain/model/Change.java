package complatablefuture.client.domain.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Value
public class Change {

    private static final BiFunction<BigDecimal,BigDecimal,BigDecimal>
    CALCULATE_CHANGE = BigDecimal::multiply;

    String currencyCode;
    BigDecimal change;

    public static Change calculate(Rate rate, BigDecimal amount) {
        return new Change(rate.getCode(), CALCULATE_CHANGE.apply(rate.getRate(),amount));
    }
}
