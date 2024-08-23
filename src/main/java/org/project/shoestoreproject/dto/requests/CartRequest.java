package org.project.shoestoreproject.dto.requests;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartRequest {
    private String userId;
    private ProductAddCartRequest productAddCartRequest;
}
