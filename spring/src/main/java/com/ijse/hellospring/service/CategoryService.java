package com.ijse.hellospring.service;

import com.ijse.hellospring.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category>getAllCategory();
    Category getCategoryById(Long id);
    Category createCategory(Category category);

}
