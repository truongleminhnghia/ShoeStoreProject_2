package org.project.shoestoreproject.DTO.Request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class UserRegister {

    private String userName;
    private String password;
}
