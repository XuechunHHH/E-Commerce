package xuechun.springboot.ecommerceapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xuechun.springboot.ecommerceapp.common.ApiResponse;
import xuechun.springboot.ecommerceapp.dto.Product.ProductDto;
import xuechun.springboot.ecommerceapp.exceptions.AuthenticationFailException;
import xuechun.springboot.ecommerceapp.model.Product;
import xuechun.springboot.ecommerceapp.model.User;
import xuechun.springboot.ecommerceapp.model.Wishlist;
import xuechun.springboot.ecommerceapp.repository.ProductRepository;
import xuechun.springboot.ecommerceapp.service.AuthenticationService;
import xuechun.springboot.ecommerceapp.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    
    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishlist(@RequestBody ProductDto productDto, @RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUserByToken(token);
        Product product = productRepository.findById(productDto.getId()).get();
        Wishlist wishlist = new Wishlist(user, product);
        wishlistService.createWishlist(wishlist);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to wishlist successfully"), HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishlist(@PathVariable("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUserByToken(token);
        List<Wishlist> wishlist = wishlistService.readWishlistByUser(user);
        List<ProductDto> products = new ArrayList<>();
        for (Wishlist w : wishlist) {
            products.add(new ProductDto(w.getProduct()));
        }
        return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
    }

}
