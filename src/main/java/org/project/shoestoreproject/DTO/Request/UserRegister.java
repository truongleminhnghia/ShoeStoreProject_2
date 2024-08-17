package org.project.shoestoreproject.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UserRegister {

    @NotBlank(message = "Username must not be blank.")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9._@-]+$", message = "Username can only contain letters, numbers, dots, underscores, dashes, and at signs.")
    private String userName;

    @NotBlank(message = "Password must not be blank.")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9._@!#$%^&*()-]+$", message = "Password can only contain letters, numbers, and special characters like . _ @ ! # $ % ^ & * ( ) -")
    private String password;
}
