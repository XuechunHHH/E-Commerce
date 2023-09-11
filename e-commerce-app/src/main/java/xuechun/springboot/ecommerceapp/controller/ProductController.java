package xuechun.springboot.ecommerceapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import xuechun.springboot.ecommerceapp.common.ApiResponse;
import xuechun.springboot.ecommerceapp.dto.Product.ProductDto;
import xuechun.springboot.ecommerceapp.service.CategoryService;
import xuechun.springboot.ecommerceapp.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto) {
        if (!categoryService.findById(productDto.getCategoryId())) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        productService.addProduct(productDto, categoryService.getCategoryById(productDto.getCategoryId()));
        return new ResponseEntity<>(new ApiResponse(true, "Product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> listProductDtos() {
        return new ResponseEntity<>(productService.listProductDtos(), HttpStatus.OK);
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody @Valid ProductDto productDto) {
        if (!categoryService.findById(productDto.getCategoryId())) {
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.CONFLICT);
        }
        if (!productService.findById(productId)) {
            return new ResponseEntity<>(new ApiResponse(false, "productId is invalid"), HttpStatus.CONFLICT);
        }
        productService.updateProduct(productId, productDto, categoryService.getCategoryById(productDto.getCategoryId()));
        return new ResponseEntity<>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }
}
