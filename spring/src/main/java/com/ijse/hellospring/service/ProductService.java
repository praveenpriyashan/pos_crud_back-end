package com.ijse.hellospring.service;

import com.ijse.hellospring.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getAllProduct();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id,Product updateProduct);
//    void deleteProduct(Long id);
}
