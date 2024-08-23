package org.project.shoestoreproject.dto.requests;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductAddCartRequest {
    private int productId;
    private String color;
    private int quantity;
    private int sizeValue;
}
