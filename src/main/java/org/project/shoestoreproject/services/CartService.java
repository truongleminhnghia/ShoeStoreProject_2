package org.project.shoestoreproject.services;

import org.project.shoestoreproject.dto.respones.CartItemRespone;
import org.project.shoestoreproject.dto.respones.CartRespone;
import org.project.shoestoreproject.entities.Cart;
import org.project.shoestoreproject.entities.CartItem;

import java.util.List;

public interface CartService {
    public Cart createCart(Cart cart);
    public Cart getCart(int id);
    public void deleteCart(int id);
    public Cart getByUser(String userId);
    public List<CartItemRespone> getCartItemRespones(List<CartItem> cartItems);
    public CartRespone convertToCartRespone(Cart cart);
}
