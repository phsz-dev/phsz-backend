package com.phsz.userservice.userserviceprovider;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenProvider {

    @Value("${phsz.jwt.secret}")
    private String secretString;
    @Value("${phsz.jwt.expiration}")
    private long validityInMilliseconds;
    private SecretKey key;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
    }

    public String createToken(String username, List<String> roles) {

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .claim("username", username)
                .claim("roles", roles)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        try {
            // 获取用户信息
            var payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
            var username = payload.get("username", String.class);
            var authorities = ((List<?>) payload.get("roles")).stream()
                    .map(authority -> new SimpleGrantedAuthority((String) authority))
                    .collect(Collectors.toList());
            /*
             输出用户信息
             System.out.println("username: " + username);
             System.out.println("authorities: " + authorities);
            */
            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        } catch (JwtException e) {
            return null;
        }
    }
}
