package xuechun.springboot.ecommerceapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xuechun.springboot.ecommerceapp.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
