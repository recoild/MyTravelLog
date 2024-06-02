package com.ramramv.springbootserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ramramv.springbootserver.user.entity.Role;
import com.ramramv.springbootserver.user.jwt.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        // AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
        private final AuthenticationConfiguration authenticationConfiguration;
        private final JwtUtil jwtUtil;

        public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JwtUtil jwtUtil) {

                this.authenticationConfiguration = authenticationConfiguration;
                this.jwtUtil = jwtUtil;
        }

        // AuthenticationManager Bean 등록
        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                http
                                .csrf((auth) -> auth.disable());

                http
                                .formLogin((auth) -> auth.disable());

                http
                                .httpBasic((auth) -> auth.disable());

                http
                                .authorizeHttpRequests((auth) -> auth
                                                // .requestMatchers("/v1/register", "/login").permitAll()
                                                // .requestMatchers("/admin", "/hello").hasRole("USER")
                                                // .anyRequest().authenticated()
                                                .anyRequest().permitAll());

                http
                                .addFilterBefore(new JwtFilter(jwtUtil), AuthenticationFilter.class);

                http
                                .addFilterAt(new AuthenticationFilter(
                                                authenticationManager(authenticationConfiguration), jwtUtil),
                                                UsernamePasswordAuthenticationFilter.class);

                http
                                .sessionManagement((session) -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}