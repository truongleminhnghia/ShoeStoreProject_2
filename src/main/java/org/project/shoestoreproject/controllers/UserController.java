package org.project.shoestoreproject.controllers;

import org.project.shoestoreproject.configs.CustomUserDetail;
import org.project.shoestoreproject.dto.requests.ChangePasswordRequest;
import org.project.shoestoreproject.dto.requests.UpdateUserRequest;
import org.project.shoestoreproject.dto.respones.ObjectRespone;
import org.project.shoestoreproject.dto.respones.UserRespone;
import org.project.shoestoreproject.entities.User;
import org.project.shoestoreproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shoeStoreProject/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PreAuthorize("hasRole('USER') or hasRole('STAFF') or hasRole('ADMIN')")
    @GetMapping("/user/{user_id}")
    public ResponseEntity<ObjectRespone> getUser(@PathVariable("user_id") String userId) {
        try {
            User user = userService.getUserByID(userId);
            if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "User not exist with ID: " + userId, null));
            UserRespone userRespone = userService.convertUserToUserRespone(user);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success", "User with IDL " + userId, userRespone));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "Erorr" + e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/update_user/{user_id}")
    public ResponseEntity<ObjectRespone> updateUser(
            @PathVariable("user_id") String userId,
            @RequestBody UpdateUserRequest userRequest) {
        try {
            User userExisting = userService.getUserByID(userId);
            if(userExisting == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "User not exist with ID: " + userId, null));
            if(userRequest.getFirstName() != null) userExisting.setFisrtName(userRequest.getFirstName());
            if(userRequest.getLastName() != null) userExisting.setLastName(userRequest.getLastName());
            if(userRequest.getEmail() != null) userExisting.setEmail(userRequest.getEmail());
            if(userRequest.getAddress() != null) userExisting.setAddress(userRequest.getAddress());
            if(userRequest.getPhone() != null) userExisting.setPhoneNumber(userRequest.getPhone());
            if(userRequest.getBirthday() != null) userExisting.setBirthDate(userRequest.getBirthday());
            boolean result = userService.updateUser(userExisting);
                if (result) return ResponseEntity.status(HttpStatus.OK)
                    .body(new ObjectRespone("Success", "Update user successfully with user_id: " + userId, userExisting));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Update user failed with user_id: " + userId, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failedv ", "Error" + e.getMessage(), null));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{user_id}")
    public ResponseEntity<ObjectRespone> changePassword(
            @PathVariable("user_id") String userId,
            @RequestBody ChangePasswordRequest passwordRequest) {
        try {
            CustomUserDetail customUserDetail = (CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(!customUserDetail.getUserId().equals(userId)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ObjectRespone("Failed", "Password change not allowed because you do not have access", null));
            }
            User user = userService.getUserByID(userId);
            if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ObjectRespone("Failed", "User not exist with ID: " + userId, null));
            if(!bCryptPasswordEncoder.matches(passwordRequest.getOldPassword(), user.getPassword()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "old password incorrect", null));
            if(!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmPassword()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "password != confirmpassword", null));
            user.setPassword(bCryptPasswordEncoder.encode(passwordRequest.getNewPassword()));
            userService.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Change password successfully with user_id: " + userId, user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ObjectRespone("Failed", "Error" + e.getMessage(), null));
        }
    }


}
