package org.project.shoestoreproject.dto.respones;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NotBlank
@Builder

public class CartItemRespone {
    private int cartItemId;
    private int quantity;
    private double price;
    private int productId;

}
