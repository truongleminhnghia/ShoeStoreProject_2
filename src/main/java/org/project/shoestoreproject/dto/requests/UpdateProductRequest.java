package org.project.shoestoreproject.dto.requests;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UpdateProductRequest {

    @Size(min = 1, max = 300, message = "Product name must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 _-]+$", message = "Product name can only contain letters, numbers, spaces, dashes, underscores, hashes, and at symbols.")
    private String productName;

    @Size(min = 1, max = 50, message = "Color must be between 1 and 100 characters.")
    @Pattern(regexp = "[a-zA-Z ]+$", message = "Color can only contain letters, spaces.")
    private String color;

    @Size(min = 1, max = 200, message = "Brand must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Brand can only contain letters, spaces.")
    private String band;

    private double price;

    private List<SizeRequest> sizeRequests;
}
