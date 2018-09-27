package complatablefuture.client.domain.service;

import complatablefuture.client.domain.model.Change;
import complatablefuture.client.domain.model.Rate;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
class ChangeCalculator {

    private final BiFunction<Double,Integer,Double>
            calculateRate = (rate, amount) -> rate * amount;

    Change calculate(Rate rate, final Integer amount) {

        return new Change(rate.getCode(), calculateRate.apply(rate.getRate(), amount));
    }

}
