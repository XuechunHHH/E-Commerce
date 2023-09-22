package xuechun.springboot.ecommerceapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xuechun.springboot.ecommerceapp.dto.Product.ProductDto;
import xuechun.springboot.ecommerceapp.exceptions.ProductNotFoundException;
import xuechun.springboot.ecommerceapp.model.Category;
import xuechun.springboot.ecommerceapp.model.Product;
import xuechun.springboot.ecommerceapp.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }
    
    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        return product;
    }

    public List<ProductDto> listProductDtos() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

    public boolean findById(Integer productId) {
        return productRepository.findById(productId).isPresent();
    }

    public Product getProductById(Integer productId) throws ProductNotFoundException{
        if (!findById(productId)) {
            throw new ProductNotFoundException("Product id is invalid " + productId);
        }
        return productRepository.findById(productId).get();
    }

    public void updateProduct(Integer productId, ProductDto productDto, Category category) {
        Product product = productRepository.findById(productId).get();
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        productRepository.save(product);
    }

}
