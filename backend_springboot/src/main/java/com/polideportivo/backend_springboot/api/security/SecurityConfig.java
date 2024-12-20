package com.polideportivo.backend_springboot.api.security;

import org.springframework.web.cors.CorsConfigurationSource;
import com.polideportivo.backend_springboot.api.exception.RestAccessDeniedHandler;
import com.polideportivo.backend_springboot.api.exception.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    
    private final DynamicProxyFilter dynamicProxyFilter;
    private final SecurityFilter securityFilter;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    private final CorsConfigurationSource corsConfigurationSource;

    private static final String[] PUBLIC_READ_ENDPOINTS = {
            "/api/entrenamientos", "/api/entrenamientos/*",
            "/api/profiles", "/api/profiles/*",
            "/api/deportes", "/api/deportes/*", "/api/deportes/**",
            "/api/pistas", "/api/pistas/*",
            "/api/pistasInfinite"
    };

    private static final String[] PUBLIC_WRITE_ENDPOINTS = {
            "/api/users", "/api/users/login", "/api/refresh", "/api/logout"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, PUBLIC_WRITE_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_READ_ENDPOINTS).permitAll()
                        .anyRequest().authenticated()
                )
                .anonymous(AbstractHttpConfigurer::disable)
                .exceptionHandling(handler -> handler
                        .accessDeniedHandler(restAccessDeniedHandler)
                        .authenticationEntryPoint(restAuthenticationEntryPoint))
                // .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
                .addFilterBefore(dynamicProxyFilter, UsernamePasswordAuthenticationFilter.class) // Agregar el filtro dinámico antes de UsernamePasswordAuthenticationFilter
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class); // Agregar el securityFilter antes de UsernamePasswordAuthenticationFilter

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
