package org.project.shoestoreproject.Controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.project.shoestoreproject.Config.CustomUserDetail;
import org.project.shoestoreproject.DTO.Request.UserLogin;
import org.project.shoestoreproject.DTO.Request.UserRegister;
import org.project.shoestoreproject.DTO.Respone.ObjectRespone;
import org.project.shoestoreproject.DTO.Respone.TokenResponse;
import org.project.shoestoreproject.Entity.Role;
import org.project.shoestoreproject.Entity.User;
import org.project.shoestoreproject.Enum.EnumRoleName;
import org.project.shoestoreproject.JWT.JWTToken;
import org.project.shoestoreproject.Logout.ListToken;
import org.project.shoestoreproject.Mapper.UserMapper;
import org.project.shoestoreproject.Services.RoleService;
import org.project.shoestoreproject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/public")
@Slf4j

public class MainController {

    //@Value("${time.reset}")
    //private int timeReset;

    @Autowired private UserService userService;
    @Autowired private RoleService roleService;
    @Autowired private JWTToken jwtToken;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private ListToken listToken;
    @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<ObjectRespone> userRegister(@Valid @RequestBody UserRegister userRegister, HttpServletRequest request) {
        try {
            Role role = roleService.getRoleByRoleName(EnumRoleName.ROLE_USER);
            String randomString = UUID.randomUUID().toString();
            boolean check = true;
            User user = userService.getUserByUsername(userRegister.getUserName());
            if(user != null) {
                check = false;
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "Username already exists", null));
            }
            user = new User(userRegister.getUserName(), null, null, null,  bCryptPasswordEncoder.encode(userRegister.getPassword()),
                    null, null, null, role);
            userService.save(user);
//        if(userRegister == null || userService.getUserByEmail(userRegister.getEmail()) != null) {
//            check = false;
//        }
//
//        String dataUrl = uploadImageService.generateImageWithInitial(userRegister.getEmail());
//        String url = uploadImageService.uploadFileBase64(dataUrl);
//        user.setAvata(url);
//
//        if(check) {
//            userService.save(user);
//            String siteUrl = request.getRequestURL().toString().replace(request.getServletPath(), "");
//            check = userService.sendVerificationEmail(user, siteUrl);
//        }
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectRespone("Success", "Create account successfully", user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectRespone("Failed", "Erorr" + e.getMessage(), null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginPage(@Valid @RequestBody UserLogin userLogin) {
        try {
            // Kiểm tra xem người dùng có tồn tại không
            User user = userService.getUserByEmail(userLogin.getUserName());
            if (user == null) {
                // Tên người dùng không tồn tại
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "The username does not exist. Please register.", null));
            }
            // Tạo đối tượng xác thực
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword());
            // Xác thực người dùng
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            // Đặt thông tin xác thực vào SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Tạo token JWT
            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
            String token = jwtToken.generatedToken(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse("Success", "Login successfully", token));
        } catch (BadCredentialsException e) {
            // Mật khẩu không đúng
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "Incorrect password.", null));
        } catch (Exception e) {
            // Xử lý các lỗi khác
            log.error("Login failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TokenResponse("Failed", "An unexpected error occurred.", null));
        }
    }


//    @PostMapping("/login")
//    public ResponseEntity<TokenResponse> loginPage(@Valid @RequestBody UserLogin userLogin) {
//        try {
////            String gRecaptchaResponse = userLogin.getRecaptchaResponse();
////            boolean check_captcha = userService.verifyRecaptcha(gRecaptchaResponse);
////            if(check_captcha) {
//            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                    new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword());
//            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//            CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            //SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            String s = jwtToken.generatedToken(userDetails);
//         //   User ss = UserMapper.customUserDetailToUser(userDetails);
////            userService.setQuantityLoginFailed(0, userDetails.getEmail());
////            userService.setTimeOffline(null, userDetails.getEmail());
////            userService.setQuantityReceiveEmailOffline(0, userDetails.getEmail());
//
//            return ResponseEntity.status(HttpStatus.OK).body(new TokenResponse("Success", "Login successfully", s));
////            } else {
////                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "Login failed", null));
////            }
//        } catch(Exception e) {
////            log.error("Cannot login : {}", e.toString());
////            User user = userService.getUserByEmail(userLogin.getUserName());
////            if (user != null) {
////                if(user.isEnabled()) {
////                    if(user.isNonLocked() == true) {
////                        int quantityLoginFailed = user.getQuantityLoginFailed();
////                        if(quantityLoginFailed == 0) {
////                            userService.setTimeLoginFailed(new Date(), user.getEmail());
////                        } else {
////                            long minus = userService.calculateSecondInMinute(user);
////                            if(minus >= timeReset) {
////                                userService.setQuantityLoginFailed(1, user.getEmail());
////                                quantityLoginFailed = 0;
////                                userService.setTimeLoginFailed(new Date(), user.getEmail());
////                            }
////                        }
////                        if(quantityLoginFailed == 5) {
////                            userService.lockedUserByEmail(userLogin.getEmail());
////                        } else userService.setQuantityLoginFailed((quantityLoginFailed + 1), userLogin.getEmail());
////                        int remainingAttempt = (5-quantityLoginFailed);
////                        if(remainingAttempt == 0) {
////                            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "your account is locked. Please contact to our admin to unlock", null));
////                        }
////                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "You have " + remainingAttempt + " password attempts left before your account is locked", null));
////                    } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "your account is locked. Please contact to our admin to unlock", null));
////                } else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "your account is not active. Please check your email for verify account", null));
////            } else
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponse("Failed", "Your username isn't exist. Please register it", null));
//        }
//    }
}
