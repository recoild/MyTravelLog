package com.ramramv.springbootserver.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ramramv.springbootserver.auth.dto.CustomUserDetailsDto;
import com.ramramv.springbootserver.auth.entity.Role;
import com.ramramv.springbootserver.auth.entity.User;
import com.ramramv.springbootserver.auth.jwt.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // request에서 헤더에서 JWT 토큰 추출
        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            if (jwtUtil.validateToken(token)) {

                // security context에 인증 정보를 저장
                String username = jwtUtil.getUsername(token);
                String role = jwtUtil.getRole(token);

                User user = new User();
                user.setUsername(username);
                user.setPassword("temppassword");
                user.setRole(Role.valueOf(role));

                CustomUserDetailsDto customUserDetails = new CustomUserDetailsDto(user);

                // 스프링 시큐리티 인증 토큰 생성
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        customUserDetails, null,
                        customUserDetails.getAuthorities());
                // 세션에 사용자 등록
                SecurityContextHolder.getContext().setAuthentication(authToken);

                filterChain.doFilter(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
