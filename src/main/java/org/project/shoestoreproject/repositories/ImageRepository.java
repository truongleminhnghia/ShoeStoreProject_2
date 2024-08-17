package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entitíes.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query("SELECT i FROM Image i WHERE i.product.productId =:productId")
    List<Image> findByProduct(@Param("productId") int productId);
}
