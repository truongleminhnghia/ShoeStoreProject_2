package org.project.shoestoreproject.dto.respones;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class ProductRespone {
    private int productId;
    private String productName;
    private String description;
    private String brand;
    private String color;
    private double price;
    private int quantity;
    private String category;
    private boolean isActive;
    private List<SizeRespone> sizes;
}
