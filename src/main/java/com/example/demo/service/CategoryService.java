package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        if (category.getId() != null && category.getId() == 0) {
            category.setId(null);
        }
        return categoryRepository.save(category);
    }
    
    public Category update(Long id, Category categoryDetails) {
        Category existingCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Category not found"));
        
        existingCategory.setName(categoryDetails.getName());
        
        return categoryRepository.save(existingCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
