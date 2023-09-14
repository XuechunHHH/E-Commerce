package xuechun.springboot.ecommerceapp.exceptions;

public class AuthenticationFailException extends Exception {
    public AuthenticationFailException(String message) {
        super(message);
    }
}
