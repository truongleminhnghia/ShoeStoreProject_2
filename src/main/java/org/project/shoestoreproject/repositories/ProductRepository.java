package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.isActive=true")
    List<Product> findAllTrue();

    @Query("SELECT p FROM Product p WHERE p.isActive=false")
    List<Product> findAllFasle();

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:productName%")
    List<Product> findAllName(@Param("productName") String productName);

    @Modifying
    @Query("UPDATE Product p SET p.isActive = false WHERE p.productId =:id")
    int updateStatus(@Param("id") int id);

    @Query("SELECT p FROM Product p " +
            "JOIN Category c ON p.category.categoryId = c.categoryId WHERE c.categoryName=:categoryName")
    List<Product> findByCategory(@Param("categoryName") String categoryName);


}
