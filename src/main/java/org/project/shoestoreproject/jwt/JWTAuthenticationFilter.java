package org.project.shoestoreproject.jwt;

import io.jsonwebtoken.lang.Strings;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.project.shoestoreproject.configs.CustomUserDetail;
import org.project.shoestoreproject.configs.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired private JWTToken jwtToken;
    @Autowired private CustomUserDetailService customUserDetailService;

    private final List<String> NON_USER = List.of(
            "/swagger-ui/**",
            "/v3/**",
            "/swagger-resources/**",
            "/public/**",
            "/swp391/api/collections/**",
            "/oauth2/**"
    );

    public String getToken(HttpServletRequest request) {
        String s = request.getHeader("Authorization");
        if(s.startsWith("Bearer ") && StringUtils.hasText(s)) {
            return s.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if(isAuthentication(request.getRequestURI())) {
                filterChain.doFilter(request, response);
                return;
            }
            String bearerToken = getToken(request);
            if(Strings.hasText(bearerToken) && jwtToken.validate(bearerToken)) {
                String username = jwtToken.getUserNameFromJwt(bearerToken);
                CustomUserDetail customUserDetail = (CustomUserDetail) customUserDetailService.loadUserByUsername(username);
                if(customUserDetail != null) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(customUserDetail, null, customUserDetail.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (Exception e) {
            log.error("Fail on set user authentication:{}", e.toString());
        }
        filterChain.doFilter(request, response);
    }

    private boolean isAuthentication(String uri){
        AntPathMatcher pathcMatcher = new AntPathMatcher();
        return NON_USER.stream().anyMatch(pattern -> pathcMatcher.match(pattern,uri));
    }
}
