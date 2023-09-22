package xuechun.springboot.ecommerceapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xuechun.springboot.ecommerceapp.dto.Cart.AddCartDto;
import xuechun.springboot.ecommerceapp.dto.Cart.CartDto;
import xuechun.springboot.ecommerceapp.dto.Cart.CartItemDto;
import xuechun.springboot.ecommerceapp.exceptions.CartItemNotFoundException;
import xuechun.springboot.ecommerceapp.model.Cart;
import xuechun.springboot.ecommerceapp.model.Product;
import xuechun.springboot.ecommerceapp.model.User;
import xuechun.springboot.ecommerceapp.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public void addToCart(AddCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, user, addToCartDto.getQuantity());
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        for (Cart cart : cartList) {
            cartItemDtos.add(new CartItemDto(cart));
        }

        double totalPrice = 0;
        for (CartItemDto cartItemDto : cartItemDtos) {
            totalPrice += cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity();
        }

        return new CartDto(cartItemDtos, totalPrice);
    }

    public void deleteCartItem(User user, Integer cartItemId) throws CartItemNotFoundException{
        Cart cart = cartRepository.findById(cartItemId).orElseThrow(() -> new CartItemNotFoundException("Cart item Id not valid"));
        if (cart.getUser().getId() != user.getId()) {
            throw new CartItemNotFoundException("Cart item does not belong to user");
        }
        cartRepository.deleteById(cartItemId);
    }
}
