package org.project.shoestoreproject.Repositories;

import org.project.shoestoreproject.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);

    @Query("SELECT c FROM Category c WHERE c.categoryName LIKE %:name%")
    List<Category> findByNameToList(@Param("name") String name);

    @Modifying
    @Query("UPDATE Category c SET c.isActive = false WHERE c.categoryId =:id")
    int updateStatus(@Param("id") int id);

    @Query("SELECT c FROM Category c WHERE c.isActive = true")
    List<Category> findByStatus();
}
