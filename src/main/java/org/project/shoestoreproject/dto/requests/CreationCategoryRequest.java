package org.project.shoestoreproject.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CreationCategoryRequest {

    @NotNull(message = "Category name must not be null.")
    @NotBlank(message = "Category name must not be blank.")
    private String categoryName;
}
