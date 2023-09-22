package xuechun.springboot.ecommerceapp.dto.Cart;

import jakarta.validation.constraints.NotNull;

public class AddCartDto {
    private Integer id;
    private @NotNull Integer productId;
    private @NotNull Integer quantity;

    public AddCartDto() {
    }

    public AddCartDto(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ",";
    }

    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
