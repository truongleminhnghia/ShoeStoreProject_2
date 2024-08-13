package org.project.shoestoreproject.DTO.Request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class SizeRequest {
    private int sizeValue;
    private int quantity;
}
