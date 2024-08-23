package org.project.shoestoreproject.dto.respones;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartItemRespone {
    private int cartItemId;
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private int size;
    private String color;
}
