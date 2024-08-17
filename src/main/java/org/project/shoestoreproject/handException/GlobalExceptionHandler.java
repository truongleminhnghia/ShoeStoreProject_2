package org.project.shoestoreproject.handException;

import org.project.shoestoreproject.dto.respones.ObjectRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ObjectRespone> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ObjectRespone("Failed", "You do not have permission to access this resource.", null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ObjectRespone> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ObjectRespone("Failed", "An unexpected error occurred: " + ex.getMessage(), null));
    }
}
