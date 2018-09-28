package complatablefuture.client.infrastructure.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;

public class FutureUtil {

    public static <T, U extends Throwable>
    T tryJoinOrThrow(CompletableFuture<T> future, Supplier<? extends U> supplier) throws U {
        try {
            return future.join();
        } catch (CompletionException e) {
            throw supplier.get();
        }
    }
}
