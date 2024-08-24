package org.project.shoestoreproject.services;

import org.project.shoestoreproject.dto.respones.CartItemRespone;
import org.project.shoestoreproject.entities.CartItem;

import java.util.List;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);
    public CartItem getItem(int cartItemId);
    public List<CartItem> getAllByCart(int cartId);
    public CartItem updateCartItem(int cartItemId, CartItem cartItem);
    public boolean deleteCartItem(int cartItemId);
    public CartItemRespone converToCartItemRespone(CartItem cartItem);
    public void updateSizeIncart(CartItem cartItem, int value);
    public void updateColorIncart(CartItem cartItem, String color);
}
