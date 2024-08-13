package org.project.shoestoreproject.DTO.Request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreateProductRequest {

    private String productName;
    private double price;
    private String description;
    private String color;
    private String brand;
    private String categoryName;
    private List<MultipartFile> images;
    private List<SizeRequest> sizeValue;

}
