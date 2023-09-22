package xuechun.springboot.ecommerceapp.exceptions;

public class AuthenticationFailException extends CustomException {
    public AuthenticationFailException(String message) {
        super(message);
    }
}
