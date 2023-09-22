package xuechun.springboot.ecommerceapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xuechun.springboot.ecommerceapp.model.Cart;
import xuechun.springboot.ecommerceapp.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
