package xuechun.springboot.ecommerceapp.dto.Cart;

import java.util.List;

public class CartDto {
    private List<CartItemDto> cartItems;
    private double totalPrice;
    
    public CartDto(List<CartItemDto> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
