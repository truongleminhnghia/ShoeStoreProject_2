package org.project.shoestoreproject.controllers;

import org.project.shoestoreproject.configs.CustomUserDetail;
import org.project.shoestoreproject.dto.requests.UpdateCartItemRequest;
import org.project.shoestoreproject.dto.respones.CartItemRespone;
import org.project.shoestoreproject.dto.respones.ObjectRespone;
import org.project.shoestoreproject.entities.Cart;
import org.project.shoestoreproject.entities.CartItem;
import org.project.shoestoreproject.services.CartItemService;
import org.project.shoestoreproject.services.CartService;
import org.project.shoestoreproject.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoeStoreProject/cart_items")
@CrossOrigin(origins = "*")
public class CartItemController {
    @Autowired private CartItemService cartItemService;
    @Autowired private CartService cartService;
    @Autowired private ProductService productService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/cart_item/{cart_item_id}")
    public ResponseEntity<ObjectRespone> getCartItem(@PathVariable("cart_item_id") int id) {
        try {
            CartItem cartItem = cartItemService.getItem(id);
            if(cartItem == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "Cart Item Not Exist With ID: " + id, null));
            Cart cart = cartService.getCart(cartItem.getCart().getCartId());
            if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "Cart Not Fount With ID: " + cartItem.getCart().getCartId(), null));
            CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(!customUserDetail.getUserId().equals(cart.getUser().getUserId()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed", "User not authorized to add this cart", null));
            CartItemRespone cartItemRespone = cartItemService.converToCartItemRespone(cartItem);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Cart Item with ID: " + id, cartItemRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Faile", "Error: " + e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/update_cart_item/{cart_item_id}")
    public ResponseEntity<ObjectRespone> updateCartItem(
            @PathVariable("cart_item_id") int id,
            @RequestBody UpdateCartItemRequest cartItemRequest
            ) {
        try {
            CartItem cartItemExisting = cartItemService.getItem(id);
            if (cartItemExisting == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "Cart Item Not Fount With ID: " + id, null));
            Cart cart = cartService.getCart(cartItemExisting.getCart().getCartId());
            if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "Cart Not Fount With ID: " + cartItemExisting.getCart().getCartId(), null));
            CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(!customUserDetail.getUserId().equals(cart.getUser().getUserId()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed", "User not authorized to add this cart", null));
            if(cartItemRequest.getSizeValue() != 0) {
                cartItemService.updateSizeIncart(cartItemExisting, cartItemRequest.getSizeValue());
            }
            if(cartItemRequest.getColor() != null) cartItemExisting.setColor(cartItemRequest.getColor());
            if(cartItemRequest.getQuantity() != 0) cartItemExisting.setQuantity(cartItemRequest.getQuantity());
            cartItemService.updateCartItem(id, cartItemExisting);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Update Cart Item Successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Faile", "Error: " + e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/cart_item/{cart_item_id}")
    public ResponseEntity<ObjectRespone> deleteCartItem(@PathVariable("cart_item_id") int id) {
        try {
            boolean result = cartItemService.deleteCartItem(id);
            if(result) return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success", "Delete cart Item successfully", null));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Delete cart Item failed", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "Error: " + e.getMessage(), null));
        }
    }
}
