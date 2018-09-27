package complatablefuture.client.app;

import complatablefuture.client.domain.model.Change;
import complatablefuture.client.domain.service.CantorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cantor")
class CantorController {

    private final CantorService cantorService;

    @GetMapping("/change/{amount}/{currencies}")
    List<Change> changeCurrency(@PathVariable Integer amount ,
                                @PathVariable List<String> currencies) {

        return cantorService.change(amount,currencies);
    }

}
