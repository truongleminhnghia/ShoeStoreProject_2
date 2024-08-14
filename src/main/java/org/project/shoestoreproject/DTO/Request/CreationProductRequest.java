package org.project.shoestoreproject.DTO.Request;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreationProductRequest {

    private String productName;
    private String brand;
    private String color;
    private double price;
    private String description;
    private String categoryName;
    private List<SizeRequest> sizes;
}
