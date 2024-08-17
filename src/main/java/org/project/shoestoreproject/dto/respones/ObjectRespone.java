package org.project.shoestoreproject.dto.respones;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Builder

public class ObjectRespone {

    private String status;

    private String message;

    private Object data;
}
