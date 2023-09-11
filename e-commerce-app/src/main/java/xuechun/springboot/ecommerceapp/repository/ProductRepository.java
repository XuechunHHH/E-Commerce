package xuechun.springboot.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import xuechun.springboot.ecommerceapp.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    
}
