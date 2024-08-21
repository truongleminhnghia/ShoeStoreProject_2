package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.CartItem;
import org.project.shoestoreproject.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImp implements CartItemService {

    @Autowired private CartItemRepository cartItemRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem getItem(int cartItemId) {
        return cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));
    }

    @Override
    public List<CartItem> getAllByCart(int cartId) {
        return cartItemRepository.findByCart(cartId);
    }

    @Override
    public CartItem updateCartItem(int cartItemId, CartItem cartItem) {
        return getItem(cartItemId) != null ? cartItemRepository.save(cartItem) : null;
    }

    @Override
    public void deleteCartItem(int cartItemId) {
        if (getItem(cartItemId) != null) {
            cartItemRepository.deleteById(cartItemId);
        }
    }
}
