package xuechun.springboot.ecommerceapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xuechun.springboot.ecommerceapp.model.User;
import xuechun.springboot.ecommerceapp.model.Wishlist;
import xuechun.springboot.ecommerceapp.repository.WishlistRepository;

@Service
public class WishlistService {
    
    @Autowired
    private WishlistRepository wishlistRepository;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    public List<Wishlist> readWishlistByUser(User user) {
        return wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);
    }
}
