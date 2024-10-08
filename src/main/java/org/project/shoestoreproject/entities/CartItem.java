package org.project.shoestoreproject.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@Data
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private int cartItemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private int size;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    public CartItem(int quantity, double price, Product product, Cart cart) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.cart = cart;
    }
}
