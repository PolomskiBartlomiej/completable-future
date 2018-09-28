package complatablefuture.client.app;

import complatablefuture.client.domain.model.Rate;
import complatablefuture.client.domain.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/{currencies}")
    List<Rate> actualCurrencies(@PathVariable List<String> currencies) {
        return currencyService.actualRate(currencies);
    }

    @GetMapping("/today/{currencies}")
    List<Rate> todayCurrencies(@PathVariable List<String> currencies) {
        return currencyService.todayRate(currencies);
    }
}
