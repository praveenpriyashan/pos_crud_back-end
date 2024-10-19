package com.ijse.hellospring.service;


import com.ijse.hellospring.entity.Product;
import com.ijse.hellospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product updateProduct) {
        Product product1 = productRepository.findById(id).orElse(null);
        if (product1 != null) {
            product1.setName(updateProduct.getName());
            product1.setPrice(updateProduct.getPrice());
            product1.setQuantity(updateProduct.getQuantity());
            product1.getCategory().setId(updateProduct.getCategory().getId());
            return productRepository.save(product1);
        }else{
            return null;
        }
    }

}
