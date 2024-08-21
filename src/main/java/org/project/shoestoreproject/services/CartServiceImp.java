package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Cart;
import org.project.shoestoreproject.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {

    @Autowired private CartRepository cartRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(int id) {
        return cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public void deleteCart(int id) {
        cartRepository.deleteById(id);
    }
}
