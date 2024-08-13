package org.project.shoestoreproject.Services;

import org.project.shoestoreproject.Entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategory(int id);
    Category updateCategory(int categoryId, Category category);
    boolean deleteCategory(int categoryId);
    List<Category> getAllCategories();
    Category getCategoryByName(String name);
    List<Category> getAllName(String name);
    List<Category> getAllStatusTrue();
}
