package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {

    Size findBySizeValue(int value);

    @Query("SELECT s FROM Size s WHERE s.product.productId =:productId")
    List<Size> findByProduct(@Param("productId") int productId);
}
