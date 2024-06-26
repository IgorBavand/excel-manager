package com.arquivos.arquivos.config;

import com.arquivos.arquivos.common.enums.ERoles;
import com.arquivos.arquivos.modules.authentication.util.JwtConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String[] permitAll = { "/api/auth",
            "/api/auth/create-new-user",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/books/**",
            "/api/columns/**"};

        return http
            .csrf(AbstractHttpConfigurer::disable)
            .oauth2ResourceServer(oauth2 -> oauth2.
                jwt(jwt -> jwt.jwtAuthenticationConverter(new JwtConverter())))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/products/create")
                .hasAnyRole(ERoles.ADMIN.name())
                .requestMatchers("/api/products")
                .hasAnyRole(ERoles.USER.name(), ERoles.ADMIN.name())
                .requestMatchers(permitAll).permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
}