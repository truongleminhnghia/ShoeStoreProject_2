package org.project.shoestoreproject.dto.requests;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private LocalDate birthday;
}
