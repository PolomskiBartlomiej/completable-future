package complatablefuture.client.domain.port;

import complatablefuture.client.domain.model.Rate;

import java.util.concurrent.CompletableFuture;

public interface BankApi {

    CompletableFuture<Rate> rate(String currency);
}
