package org.project.shoestoreproject.services;

import jakarta.persistence.EntityNotFoundException;
import org.project.shoestoreproject.entities.Category;
import org.project.shoestoreproject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category not found"));
    }

    @Override
    public Category updateCategory(int categoryId, Category category) {
         Category existingCategory = getCategory(categoryId);
         if(existingCategory == null) throw new RuntimeException("Category not found");
         existingCategory.setCategoryName(category.getCategoryName());
         return categoryRepository.save(existingCategory);
    }

    @Override
    @Transactional
    public boolean deleteCategory(int categoryId) {
        Category category = getCategory(categoryId);
        if(category == null) throw new RuntimeException("Category not found");
        int cate = categoryRepository.updateStatus(categoryId);
        if (cate > 0) return true;
        return false;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    @Override
    public List<Category> getAllName(String name) {
        return categoryRepository.findByNameToList(name);
    }

    @Override
    public List<Category> getAllStatusTrue() {
        return categoryRepository.findByStatus();
    }
}
