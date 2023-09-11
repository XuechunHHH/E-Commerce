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

	public List<Category> listCategories() {
		return categoryRepository.findAll();
	}

	public void createCategory(Category category) {
		categoryRepository.save(category);
	}

	public boolean findById(Integer categoryId) {
		return categoryRepository.findById(categoryId).isPresent();
	}

	public Category getCategoryById(Integer categoryId) {
		return categoryRepository.findById(categoryId).get();
	}

	public void updateCategory(Integer categoryID, Category newCategory) {
		Category category = categoryRepository.findById(categoryID).get();
		category.setCategoryName(newCategory.getCategoryName());
		category.setDescription(newCategory.getDescription());
		category.setImageUrl(newCategory.getImageUrl());
		categoryRepository.save(category);
	}
}