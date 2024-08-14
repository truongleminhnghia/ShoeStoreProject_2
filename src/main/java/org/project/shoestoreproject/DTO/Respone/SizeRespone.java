package org.project.shoestoreproject.DTO.Respone;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class SizeRespone {
    private int sizeId;
    private int sizeValue;
    private int quantity;
}
