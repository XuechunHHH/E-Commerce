package xuechun.springboot.ecommerceapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xuechun.springboot.ecommerceapp.model.Category;
import xuechun.springboot.ecommerceapp.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
	private CategoryRepository categoryRepository;

    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> listCategory() {
        return categoryRepository.findAll();
    }
}
