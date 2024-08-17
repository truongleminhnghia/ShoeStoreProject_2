package org.project.shoestoreproject.DTO.Request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class SizeRequest {

    @NotNull(message = "Shoe size must not be null.")
    @Min(value = 30, message = "Shoe size must be at least 35.")
    @Max(value = 46, message = "Shoe size must not exceed 44.")
    private int sizeValue;

    @NotNull(message = "Size quantity must not be null")
    @Min(value = 0, message = "Size quantity must be at least 0")
    private int quantity;
}
