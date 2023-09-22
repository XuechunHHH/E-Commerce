package xuechun.springboot.ecommerceapp.exceptions;

public class ProductNotFoundException extends CustomException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
