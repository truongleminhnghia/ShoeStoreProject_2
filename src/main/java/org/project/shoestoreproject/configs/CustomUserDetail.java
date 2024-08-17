package org.project.shoestoreproject.configs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.project.shoestoreproject.entities.Role;
import org.project.shoestoreproject.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomUserDetail implements UserDetails {

    private String userName;
    private String password;
    private Collection<GrantedAuthority> grantedAuthorities;

    public static CustomUserDetail mapUserToUserDetail(User user) {
        Role role = user.getRole();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName().name());
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(simpleGrantedAuthority);

        CustomUserDetail customUserDetail = CustomUserDetail.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .grantedAuthorities(roles)
                .build();
        return customUserDetail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
