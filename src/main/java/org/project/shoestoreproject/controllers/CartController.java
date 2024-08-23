package org.project.shoestoreproject.controllers;

import org.project.shoestoreproject.configs.CustomUserDetail;
import org.project.shoestoreproject.dto.requests.ProductAddCartRequest;
import org.project.shoestoreproject.dto.respones.CartRespone;
import org.project.shoestoreproject.dto.respones.ObjectRespone;
import org.project.shoestoreproject.entities.Cart;
import org.project.shoestoreproject.entities.CartItem;
import org.project.shoestoreproject.entities.Product;
import org.project.shoestoreproject.entities.User;
import org.project.shoestoreproject.services.CartItemService;
import org.project.shoestoreproject.services.CartService;
import org.project.shoestoreproject.services.ProductService;
import org.project.shoestoreproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shoeStoreProject/carts")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired private CartService cartService;
    @Autowired private CartItemService cartItemService;
    @Autowired private ProductService productService;
    @Autowired private UserService userService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create_cart/{user_id}")
    public ResponseEntity<ObjectRespone> createCart(@PathVariable("user_id") String user_id) {
        try {
            User user = userService.getUserByID(user_id);
            if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "User not existing with ID: " + user_id, null));
            Cart cart = cartService.getByUser(user.getUserId());
            if(cart == null) {
                cart = new Cart();
                cart.setUser(user);
            }
            cartService.createCart(cart);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Cart created or already exists", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "Error: " + e.getMessage(), null));
        }
    }


    @PreAuthorize("hasRole('USER')")
    @PostMapping("/add_cart/{user_id}")
    public ResponseEntity<ObjectRespone> addCart(
            @PathVariable("user_id") String userId,
            @RequestBody ProductAddCartRequest productAddCartRequest) {
        try {
            CustomUserDetail userDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(!userDetail.getUserId().equals(userId))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed", "User not authorized to add this cart", null));
            Cart cart = cartService.getByUser(userId);
            if(cart == null) {
                cart = new Cart();
                cart.setUser(userService.getUserByID(userId));
                cartService.createCart(cart);
            }
            Product product = productService.getProductByProductId(productAddCartRequest.getProductId());
            if(product == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ObjectRespone("Failed", "Product not existing with ID: " + productAddCartRequest.getProductId(), null));
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(productAddCartRequest.getQuantity());
            cartItem.setPrice(product.getPrice());
            cartItem.setColor(productAddCartRequest.getColor());
            cartItem.setSize(productAddCartRequest.getSizeValue());
            cartItemService.createCartItem(cartItem);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Cart add successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "Error: " + e.getMessage(), null));
        }
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/cart/{cart_id}")
    public ResponseEntity<ObjectRespone> getCartByCartId(@PathVariable("cart_id") int cartId) {
        try {
            Cart cartExisting = cartService.getCart(cartId);
            if(cartExisting == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "Cart not existing with ID: " + cartId, null));
            CartRespone cartRespone = new CartRespone();
            cartRespone.setCartId(cartExisting.getCartId());
            cartRespone.setUserId(cartExisting.getUser().getUserId());
            cartRespone.setCartItemRespones(cartService.getCartItemRespones(cartExisting.getCartItems()));
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Cart get successfully", cartRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "Error: " + e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<ObjectRespone> getByUser(@PathVariable("user_id") String userId) {
        try {
            CustomUserDetail userDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(!userDetail.getUserId().equals(userId))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ObjectRespone("Failed", "User not authorized to add this cart", null));
            Cart cart = cartService.getByUser(userId);
            if (cart == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "Cart not existing with ID: " + userId, null));
            CartRespone cartRespone = cartService.convertToCartRespone(cart);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Cart get successfully", cartRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Error: " + e.getMessage(), null));
        }
    }
}
