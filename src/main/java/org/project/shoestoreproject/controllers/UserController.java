package org.project.shoestoreproject.controllers;

import org.project.shoestoreproject.dto.respones.ObjectRespone;
import org.project.shoestoreproject.entities.User;
import org.project.shoestoreproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoeStoreProject/users")
@CrossOrigin(origins = "**")
public class UserController {

    @Autowired private UserService userService;

    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('ADMIN')")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<ObjectRespone> getUser(@PathVariable("user_id") String userId) {
        try {
            User user = userService.getUserByID(userId);
            if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "User not exist with ID: " + userId, null));
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success", "User with IDL " + userId, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failedv ", "Erorr" + e.getMessage(), null));
        }
    }

}
