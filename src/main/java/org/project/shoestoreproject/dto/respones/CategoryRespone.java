package org.project.shoestoreproject.dto.respones;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class CategoryRespone {
    private int categoryId;
    private String categoryName;
    private boolean status;
}
