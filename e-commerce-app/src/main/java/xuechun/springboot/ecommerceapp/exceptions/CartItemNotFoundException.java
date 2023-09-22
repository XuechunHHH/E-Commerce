package xuechun.springboot.ecommerceapp.exceptions;

public class CartItemNotFoundException extends CustomException {
    public CartItemNotFoundException(String message) {
        super(message);
    }
}
