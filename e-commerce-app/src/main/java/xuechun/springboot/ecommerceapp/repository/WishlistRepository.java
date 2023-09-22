package xuechun.springboot.ecommerceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xuechun.springboot.ecommerceapp.model.User;
import xuechun.springboot.ecommerceapp.model.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{
    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);
}
