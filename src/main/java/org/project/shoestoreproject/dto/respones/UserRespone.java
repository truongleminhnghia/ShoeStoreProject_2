package org.project.shoestoreproject.dto.respones;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@NotBlank
@Data
@Builder

public class UserRespone {
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String roleName;
    private LocalDate birthDate;
}
