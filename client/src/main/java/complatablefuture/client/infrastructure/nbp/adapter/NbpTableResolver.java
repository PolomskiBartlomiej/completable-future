package complatablefuture.client.infrastructure.nbp.adapter;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

@Component
class NbpTableResolver {

    private static final Map<String,String>TABLE_A =
            ImmutableMap.<String,String>builder()
            .put("aud","a")
            .put("usd","a")
            .put("thb","a")
            .put("cad","a")
            .put("nzd","a")
            .put("sgd","a")
            .put("eur","a")
            .build();

    private static final Map<String,String> TABLE_B =
             ImmutableMap.<String,String>builder()
            .put("afn","b")
            .put("mga","b")
            .put("pab","b")
            .put("etb","b")
            .put("ves","b")
            .put("bob","b")
            .build();


    private static final Map<String,String> TABLE_C =
            ImmutableMap.<String,String>builder()
            .put("gbp","c")
            .put("jpy","c")
            .put("czk","c")
            .put("dkk","c")
            .put("nok","c")
            .build();


    String resolve(String currency) {
       return Stream.of(TABLE_A,TABLE_B,TABLE_C)
                .filter(map -> map.containsKey(currency))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("illegal currency in NBP"))
                .get(currency);
    }
}
