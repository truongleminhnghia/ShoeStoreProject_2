package org.project.shoestoreproject.DTO.Respone;

import com.fasterxml.jackson.annotation.JsonView;
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
