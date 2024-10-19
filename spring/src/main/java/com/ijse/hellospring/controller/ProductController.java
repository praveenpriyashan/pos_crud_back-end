package com.ijse.hellospring.controller;

import com.ijse.hellospring.dto.ProductDto;
import com.ijse.hellospring.entity.Product;
import com.ijse.hellospring.service.CategoryService;
import com.ijse.hellospring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products")
    public List<Product> getAllProduct()  {
        return productService.getAllProduct();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product>  getProductById(@PathVariable Long id) {
        Product product =productService.getProductById(id);
        if (product==null){
            return ResponseEntity.status(404).build();
        }else {
            return ResponseEntity.ok(product);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(categoryService.getCategoryById(productDto.getCategoryId()));

        Product createProduct= productService.createProduct(product);
        return ResponseEntity.status(201).body(createProduct);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        Product product1 = new Product();
        product1.setName(productDto.getName());
        product1.setPrice(productDto.getPrice());
        product1.setQuantity(productDto.getQuantity());
        product1.setCategory(categoryService.getCategoryById(productDto.getCategoryId()));
        return productService.updateProduct(id,product1);
    }
}
