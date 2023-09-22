package xuechun.springboot.ecommerceapp.dto.Cart;

import jakarta.validation.constraints.NotNull;
import xuechun.springboot.ecommerceapp.model.Cart;
import xuechun.springboot.ecommerceapp.model.Product;

public class CartItemDto {
    private Integer id;
    private @NotNull Product product;
    private @NotNull Integer quantity;

    public CartItemDto(Cart cart) {
        this.id = cart.getId();
        this.product = cart.getProduct();
        this.quantity = cart.getQuantity();
    }

    public Integer getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
