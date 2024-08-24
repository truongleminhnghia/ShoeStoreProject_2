package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
}
