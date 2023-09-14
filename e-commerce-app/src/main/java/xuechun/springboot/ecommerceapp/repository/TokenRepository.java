package xuechun.springboot.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xuechun.springboot.ecommerceapp.model.AuthenticationToken;
import xuechun.springboot.ecommerceapp.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>{
    AuthenticationToken findByToken(String token);
    AuthenticationToken findByUser(User user);
}
