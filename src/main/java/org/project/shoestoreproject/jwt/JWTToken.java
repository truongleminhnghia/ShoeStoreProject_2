package org.project.shoestoreproject.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.project.shoestoreproject.configs.CustomUserDetail;
import org.project.shoestoreproject.logout.ListToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@Service
public class JWTToken {
    @Autowired private ListToken listToken;

    @Value("${jwt.expiration}")
    private int JWT_EXPIRATION;  // 10 ngay

    @Value("${jwt.secret}")
    private String sceretString;

    @Value("${jwt.algorithms}")
    private String algorithm;

    private SecretKey SCRET_KEY;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(sceretString.getBytes(StandardCharsets.UTF_8));
        this.SCRET_KEY = new SecretKeySpec(keyBytes, algorithm);
    }

    public String generatedToken(CustomUserDetail customUserDetail) {
        Date date = new Date(System.currentTimeMillis());

        Date exp = new Date(System.currentTimeMillis() + JWT_EXPIRATION);

        return Jwts.builder()
                .subject(customUserDetail.getUsername())
                .issuedAt(date)
                .expiration(exp)
                .claim("userId", customUserDetail.getUserId())
                .claim("username", customUserDetail.getUsername())
                .claim("password", customUserDetail.getPassword())
                .claim("role", customUserDetail.getGrantedAuthorities())
                .signWith(SCRET_KEY)
                .compact();
    }

    public String getUserNameFromJwt(String token) {
        return getClaims(token, Claims::getSubject);
    }

    private <T> T getClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(
                Jwts.parser().verifyWith(SCRET_KEY).build().parseSignedClaims(token).getPayload());
    }
    public boolean validate(String token) {
        if(getUserNameFromJwt(token) != null && !isExpired(token) && !listToken.isListToken(token)) {
            return true;
        }
        return false;
    }

    public boolean isExpired(String token) {
        return getClaims(token, Claims::getExpiration).before(new Date(System.currentTimeMillis()));
    }

}
