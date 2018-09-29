package complatablefuture.client.domain.service;

import complatablefuture.client.domain.model.Change;
import complatablefuture.client.domain.model.Rate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component
class ChangeCalculator {

    Change calculate(Rate rate, BigDecimal amount) {
       return Change.calculate(rate, amount);
    }

}
