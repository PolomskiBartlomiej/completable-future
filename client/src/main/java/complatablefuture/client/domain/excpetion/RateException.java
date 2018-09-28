package complatablefuture.client.domain.excpetion;

public class RateException extends RuntimeException {

    public RateException() {
        super("Cannot get rate from currency");
    }
}
