package org.project.shoestoreproject.services;

import org.project.shoestoreproject.dto.respones.CartItemRespone;
import org.project.shoestoreproject.dto.respones.CartRespone;
import org.project.shoestoreproject.entities.Cart;
import org.project.shoestoreproject.entities.CartItem;
import org.project.shoestoreproject.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Cart getByUser(String userId) {
        return cartRepository.findByUser(userId);
    }

    @Override
    public List<CartItemRespone> getCartItemRespones(List<CartItem> cartItems) {
        List<CartItemRespone> cartItemRespones = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            CartItemRespone cartItemRespone = new CartItemRespone();
            cartItemRespone.setCartItemId(cartItem.getCartItemId());
            cartItemRespone.setProductId(cartItem.getProduct().getProductId());
            cartItemRespone.setProductName(cartItem.getProduct().getProductName());
            cartItemRespone.setQuantity(cartItem.getQuantity());
            cartItemRespone.setPrice(cartItem.getPrice());
            cartItemRespone.setColor(cartItem.getColor());
            cartItemRespone.setSize(cartItem.getSize());
            cartItemRespones.add(cartItemRespone);
        }
        return cartItemRespones;
    }

    @Override
    public CartRespone convertToCartRespone(Cart cart) {
        CartRespone cartRespone = new CartRespone();
        cartRespone.setCartId(cart.getCartId());
        cartRespone.setUserId(cart.getUser().getUserId());
        cartRespone.setCartItemRespones(getCartItemRespones(cart.getCartItems()));
        return cartRespone;
    }
}
