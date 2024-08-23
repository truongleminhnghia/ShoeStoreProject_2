package org.project.shoestoreproject.repositories;

import org.project.shoestoreproject.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId =:cartId")
    public List<CartItem> findByCart(@Param("cartId") int cartId);
}
