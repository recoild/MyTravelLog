package com.ramramv.springbootserver.auth.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ramramv.springbootserver.auth.dto.CustomUserDetailsDto;
import com.ramramv.springbootserver.auth.entity.Role;
import com.ramramv.springbootserver.auth.entity.User;
import com.ramramv.springbootserver.auth.service.CustomUserDetailsService;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final SecretKey key;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(@Value("${jwt.secret}") String secret, JwtUtil jwtUtil,
            CustomUserDetailsService userDetailsService) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            try {
                username = jwtUtil.getUsername(jwt);
            } catch (SignatureException e) {
                // JWT 서명 검증 실패
                throw new RuntimeException("Invalid JWT signature");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);

        // if (auth != null && auth.startsWith("Bearer ")) {
        // String token = auth.substring(7);
        // if (jwtUtil.validateToken(token)) {

        // // security context에 인증 정보를 저장
        // String username = jwtUtil.getUsername(token);
        // String role = jwtUtil.getRole(token);

        // User user = new User();
        // user.setUsername(username);
        // user.setPassword("temppassword");
        // user.setRole(Role.valueOf(role));

        // CustomUserDetailsDto customUserDetails = new CustomUserDetailsDto(user);

        // // 스프링 시큐리티 인증 토큰 생성
        // UsernamePasswordAuthenticationToken authToken = new
        // UsernamePasswordAuthenticationToken(
        // customUserDetails, null,
        // customUserDetails.getAuthorities());
        // // 세션에 사용자 등록
        // SecurityContextHolder.getContext().setAuthentication(authToken);

        // filterChain.doFilter(request, response);
        // return;
        // }
        // }

        // filterChain.doFilter(request, response);
    }
}
