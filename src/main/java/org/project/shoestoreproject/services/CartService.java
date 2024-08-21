package org.project.shoestoreproject.services;

import org.project.shoestoreproject.entities.Cart;

public interface CartService {
    public Cart createCart(Cart cart);
    public Cart getCart(int id);
    public void deleteCart(int id);
}
