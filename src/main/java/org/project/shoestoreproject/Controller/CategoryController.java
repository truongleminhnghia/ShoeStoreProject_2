package org.project.shoestoreproject.Controller;

import org.project.shoestoreproject.DTO.Request.CreationCategoryRequest;
import org.project.shoestoreproject.DTO.Respone.CategoryRespone;
import org.project.shoestoreproject.DTO.Respone.ObjectRespone;
import org.project.shoestoreproject.Entity.Category;
import org.project.shoestoreproject.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shoeStoreProject/categories")
public class CategoryController {

    @Autowired private CategoryService categoryService;

    @PostMapping("/create_category")
    public ResponseEntity<ObjectRespone> createCategory(@RequestBody CreationCategoryRequest categoryRequest) {
        Category newCategory = new Category();
        try {
            if(categoryRequest == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed","Category request null",null));
            }
            newCategory.setCategoryName(categoryRequest.getCategoryName());
            categoryService.createCategory(newCategory);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success","Create new category success",newCategory));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Message" + e.getMessage(),null));
        }
    }

    @GetMapping("/get_all")
    public ResponseEntity<ObjectRespone> getAllCategory() {
        try {
            List<Category> list = categoryService.getAllCategories();
            List<CategoryRespone> listRespone = new ArrayList<>();
            for(Category category : list) {
                CategoryRespone categoryRespone = new CategoryRespone();
                categoryRespone.setCategoryId(category.getCategoryId());
                categoryRespone.setCategoryName(category.getCategoryName());
                categoryRespone.setStatus(category.isActive());
                listRespone.add(categoryRespone);
            }
            if(listRespone.isEmpty() || listRespone == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed","List category null",null));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success","List Category", listRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed","Erorr" + e.getMessage(),null));
        }
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ObjectRespone> getCategoryById(@PathVariable("categoryId") int categoryId) {
        try {
            Category category = categoryService.getCategory(categoryId);
            if(category == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed","Category not found",null));
            CategoryRespone categoryRespone = new CategoryRespone();
            categoryRespone.setCategoryId(category.getCategoryId());
            categoryRespone.setCategoryName(category.getCategoryName());
            categoryRespone.setStatus(category.isActive());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success","Category by Id: " + categoryId , categoryRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed","Erorr" + e.getMessage(),null));
        }
    }

    @GetMapping("/category_name/{categoryName}")
    public ResponseEntity<ObjectRespone> getCategoryByName(@PathVariable("categoryName") String categoryName) {
        try {
            Category category = categoryService.getCategoryByName(categoryName);
            if(category == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed","Category not found",null));
            CategoryRespone categoryRespone = new CategoryRespone();
            categoryRespone.setCategoryId(category.getCategoryId());
            categoryRespone.setCategoryName(category.getCategoryName());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success","Category by category name: " + categoryName , categoryRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed","Erorr" + e.getMessage(),null));
        }
    }

    @GetMapping("/list_category/{categoryName}")
    public ResponseEntity<ObjectRespone> getCategoryList(@PathVariable("categoryName") String categoryName) {
        try {
            List<Category> categoryList = categoryService.getAllName(categoryName);
            List<CategoryRespone> listRespone = new ArrayList<>();
            for(Category category : categoryList) {
                CategoryRespone categoryRespone = new CategoryRespone();
                categoryRespone.setCategoryId(category.getCategoryId());
                categoryRespone.setCategoryName(category.getCategoryName());
                categoryRespone.setStatus(category.isActive());
                listRespone.add(categoryRespone);
            }
            if (listRespone.isEmpty() || listRespone == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed","List category null",null));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success","List Category", listRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed","Erorr" + e.getMessage(),null));
        }
    }

    @PostMapping("/update_category/{catgegoryId}")
    public ResponseEntity<ObjectRespone> updateCategory(@PathVariable("catgegoryId") int categoryId, @RequestBody CreationCategoryRequest categoryRequest) {
        try {
            Category category = categoryService.getCategory(categoryId);
            if (category == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed","Category not found",null));
            categoryService.updateCategory(categoryId, category);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success","Category updated successfully", category));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed","Erorr" + e.getMessage(),null));
        }
    }

    @GetMapping("/delete_category/{categoryId}")
    public ResponseEntity<ObjectRespone> deleteCategory(@PathVariable("categoryId") int categoryId) {
        try {
            Category category = categoryService.getCategory(categoryId);
            if (category == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed","Category not found",null));
            boolean cate = categoryService.deleteCategory(categoryId);
            if(cate) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ObjectRespone("Success","Category deleted successfully", cate));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed","Category deleted failed", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed","Erorr" + e.getMessage(),null));
        }
    }

    @GetMapping("/get_all_true")
    public ResponseEntity<ObjectRespone> getAllStatusTrue() {
        try {
                List<Category> list = categoryService.getAllStatusTrue();
                List<CategoryRespone> listRespone = new ArrayList<>();
                for(Category category : list) {
                    CategoryRespone categoryRespone = new CategoryRespone();
                    categoryRespone.setCategoryId(category.getCategoryId());
                    categoryRespone.setCategoryName(category.getCategoryName());
                    categoryRespone.setStatus(category.isActive());
                    listRespone.add(categoryRespone);
                }
                if(listRespone.isEmpty() || listRespone == null)
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ObjectRespone("Failed","List category null",null));
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ObjectRespone("Success","List Category", listRespone));
        } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed","Erorr" + e.getMessage(),null));
        }
    }
}
