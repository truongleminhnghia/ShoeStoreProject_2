package org.project.shoestoreproject.dto.requests;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateCartItemRequest {
    private int quantity;
    private int sizeValue;
    private String color;
}
