package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM  Cart c WHERE c.user.userId =:userId")
    public Cart findByUser(@Param("userId") String userId);
}
