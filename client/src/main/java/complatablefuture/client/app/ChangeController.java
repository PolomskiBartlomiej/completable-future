package complatablefuture.client.app;

import complatablefuture.client.domain.model.Change;
import complatablefuture.client.domain.service.ChangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/change")
class ChangeController {

    private final ChangeService changeService;

    @GetMapping("/{amount}/{currencies}")
    List<Change> changeCurrency(@PathVariable BigDecimal amount ,
                                @PathVariable List<String> currencies) {
        return changeService.change(amount,currencies);
    }

}
