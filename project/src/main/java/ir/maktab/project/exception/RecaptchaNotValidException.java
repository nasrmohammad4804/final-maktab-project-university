package ir.maktab.project.exception;
import org.springframework.security.core.AuthenticationException;

public class RecaptchaNotValidException extends AuthenticationException {
    public RecaptchaNotValidException(String explanation) {
        super(explanation);
    }
}
