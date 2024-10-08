package org.project.shoestoreproject.dto.respones;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class TokenResponse {

    private String code;
    private String message;
    private String token;
}
