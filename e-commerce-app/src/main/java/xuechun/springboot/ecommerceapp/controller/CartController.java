package xuechun.springboot.ecommerceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xuechun.springboot.ecommerceapp.common.ApiResponse;
import xuechun.springboot.ecommerceapp.dto.Cart.AddCartDto;
import xuechun.springboot.ecommerceapp.dto.Cart.CartDto;
import xuechun.springboot.ecommerceapp.exceptions.AuthenticationFailException;
import xuechun.springboot.ecommerceapp.exceptions.CartItemNotFoundException;
import xuechun.springboot.ecommerceapp.exceptions.ProductNotFoundException;
import xuechun.springboot.ecommerceapp.model.Product;
import xuechun.springboot.ecommerceapp.model.User;
import xuechun.springboot.ecommerceapp.service.AuthenticationService;
import xuechun.springboot.ecommerceapp.service.CartService;
import xuechun.springboot.ecommerceapp.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddCartDto addToCartDto, @RequestParam("token") String token) throws ProductNotFoundException, AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUserByToken(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<CartDto> listCartItems(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUserByToken(token);
        return new ResponseEntity<>(cartService.listCartItems(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@RequestParam("token") String token, @PathVariable("cartItemId") Integer cartItemId) throws AuthenticationFailException, CartItemNotFoundException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUserByToken(token);
        cartService.deleteCartItem(user, cartItemId);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted cart item"), HttpStatus.OK);
    }
}
