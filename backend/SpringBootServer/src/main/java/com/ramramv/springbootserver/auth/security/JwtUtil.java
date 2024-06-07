package com.ramramv.springbootserver.auth.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtUtil {
    private final SecretKey key;
    // private final long jwtExpirationInMs;
    // private final long jwtRefreshExpirationInMs;

    public JwtUtil(@Value("${jwt.secret}") String secret
    // @Value("${jwt.expiration}") long jwtExpirationInMs,
    // @Value("${jwt.refresh-token.expiration}") long jwtRefreshExpirationInMs
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        // this.jwtExpirationInMs = jwtExpirationInMs;
        // this.jwtRefreshExpirationInMs = jwtRefreshExpirationInMs;
    }

    // // JWT 토큰 생성 메서드
    // public String generateToken(Authentication authentication) {
    // // 권한 정보 추출
    // String authorities = authentication.getAuthorities().stream()
    // .map(GrantedAuthority::getAuthority)
    // .collect(Collectors.joining(","));

    // long now = (new Date()).getTime();

    // // Access Token 생성
    // Date expiryDate = new Date(now + jwtExpirationInMs);
    // return Jwts.builder()
    // .subject(authentication.getName())
    // .claim("auth", authorities)
    // .issuedAt(new Date(now))
    // .expiration(expiryDate)
    // .signWith(key, Jwts.SIG.HS256)
    // .compact();
    // }

    // // Refresh Token 생성 메서드
    // public String generateRefreshToken(Authentication authentication) {
    // // 권한 정보 추출
    // String authorities = authentication.getAuthorities().stream()
    // .map(GrantedAuthority::getAuthority)
    // .collect(Collectors.joining(","));

    // long now = (new Date()).getTime();

    // // Refresh Token 생성
    // Date expiryDate = new Date(now + jwtRefreshExpirationInMs);
    // return Jwts.builder()
    // .subject(authentication.getName())
    // .claim("auth", authorities)
    // .issuedAt(new Date(now))
    // .expiration(expiryDate)
    // .signWith(key, Jwts.SIG.HS256)
    // .compact();
    // }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload()
                .getSubject();
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload()
                .getExpiration();
        return expiration.before(new Date());
    }

    public String getUsername(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("auth", String.class);
    }

}
