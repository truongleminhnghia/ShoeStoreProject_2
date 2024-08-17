package org.project.shoestoreproject.dto.requests;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreationProductRequest {

    @NotBlank(message = "Product name must not be blank.")
    @NotNull(message = "Product name must not be null.")
    @Size(min = 1, max = 300, message = "Product name must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 _-]+$", message = "Product name can only contain letters, numbers, spaces, dashes, underscores, hashes, and at symbols.")
    private String productName;

    @NotBlank(message = "Brand must not be blank.")
    @NotNull(message = "Brand name must not be null.")
    @Size(min = 1, max = 200, message = "Brand must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Brand can only contain letters, spaces.")
    private String brand;

    @NotBlank(message = "Color name must not be blank.")
    @NotNull(message = "Color name must not be null.")
    @Size(min = 1, max = 50, message = "Color must be between 1 and 100 characters.")
    @Pattern(regexp = "[a-zA-Z ]+$", message = "Color can only contain letters, spaces.")
    private String color;

    @NotNull(message = "Price must not be null.")
    @Min(value = 0, message = "Price must be at least 0.")
    private double price;

    private String description;

    @NotNull(message = "Category name must not be null.")
    @NotBlank(message = "Category name must not be blank.")
    @Size(min = 1, max = 200, message = "Color must be between 1 and 100 characters.")
    @Pattern(regexp = "[a-zA-Z ]+$", message = "Color can only contain letters, spaces.")
    private String categoryName;

    private List<SizeRequest> sizes;
}
