package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

    @Query("SELECT c FROM Color c " +
            "JOIN ProductColor pc ON c.colorId = pc.color.colorId " +
            "JOIN Product p ON p.productId = pc.product.productId " +
            "WHERE p.productId = :productId")
    List<Color> findByProduct(@Param("productId") int productId);

    @Query("SELECT c FROM Color c WHERE c.colorName=:name")
    public Color findByColorName(@Param("name") String colorName);
}
