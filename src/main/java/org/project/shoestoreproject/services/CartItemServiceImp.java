package org.project.shoestoreproject.services;

import org.project.shoestoreproject.dto.respones.CartItemRespone;
import org.project.shoestoreproject.entities.CartItem;
import org.project.shoestoreproject.entities.Size;
import org.project.shoestoreproject.repositories.CartItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImp implements CartItemService {

    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImp.class);


    private static final Logger log = LoggerFactory.getLogger(CartItemServiceImp.class);
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private ProductService productService;
    @Autowired private SizeService sizeService;

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
    public boolean deleteCartItem(int cartItemId) {
        if (getItem(cartItemId) != null) {
            cartItemRepository.deleteById(cartItemId);
            return true;
        }
        return false;
    }

    @Override
    public CartItemRespone converToCartItemRespone(CartItem cartItem) {
        CartItemRespone cartItemRespone = new CartItemRespone();
        cartItemRespone.setCartItemId(cartItem.getCartItemId());
        cartItemRespone.setProductId(cartItem.getProduct().getProductId());
        cartItemRespone.setProductName(cartItem.getProduct().getProductName());
        cartItemRespone.setQuantity(cartItem.getQuantity());
        cartItemRespone.setPrice(cartItem.getPrice());
        cartItemRespone.setColor(cartItem.getColor());
        cartItemRespone.setSize(cartItem.getSize());
        return cartItemRespone;
    }

    @Override
    public void updateSizeIncart(CartItem cartItem, int value) {
        List<Size> listSizeByProduct = sizeService.getSizeByProduct(cartItem.getProduct().getProductId());
        if (listSizeByProduct == null || listSizeByProduct.isEmpty()) {
            String errorMessage = "No sizes available for the product with ID: " + cartItem.getProduct().getProductId();
            logger.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        for (Size size : listSizeByProduct) {
            if (size.getSizeValue() == value) {
                cartItem.setSize(value);
            }
        }
        String errorMessage = "Size value " + value + " is not available for the product with ID: " + cartItem.getProduct().getProductId();
        logger.error(errorMessage);
        throw new RuntimeException(errorMessage);
    }

    @Override
    public void updateColorIncart(CartItem cartItem, String color) {
        
    }
}
