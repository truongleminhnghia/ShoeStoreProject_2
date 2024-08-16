package org.project.shoestoreproject.Mapper;

import org.project.shoestoreproject.Config.CustomUserDetail;
import org.project.shoestoreproject.Entity.Role;
import org.project.shoestoreproject.Entity.User;
import org.project.shoestoreproject.Enum.EnumRoleName;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static User customUserDetailToUser(CustomUserDetail customUserDetail) {

        Collection<GrantedAuthority> grantedAuthorities = customUserDetail.getGrantedAuthorities();

        List<EnumRoleName> roles= grantedAuthorities.stream()
                .map(authorityName -> {
                    String roleName = authorityName.getAuthority();
                    return EnumRoleName.valueOf(roleName);
                }).collect(Collectors.toList());

        Role role = new Role();
        for(EnumRoleName role_enum : roles) {
            role.setRoleName(role_enum);
        }

        User user = User.builder()
                .userName(customUserDetail.getUsername())
                .password(customUserDetail.getPassword())
                .role(role)
                .build();
        return user;
    }
}
