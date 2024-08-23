package org.project.shoestoreproject.dto.respones;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class CartRespone {
    private int cartId;
    private String userId;
    private List<CartItemRespone> cartItemRespones;
}
