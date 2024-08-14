package org.project.shoestoreproject.DTO.Request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UpdateProductRequest {
    private String productName;
    private String color;
    private String band;
    private double price;
    private int quantity;
    private List<SizeRequest> sizeRequests;
}
