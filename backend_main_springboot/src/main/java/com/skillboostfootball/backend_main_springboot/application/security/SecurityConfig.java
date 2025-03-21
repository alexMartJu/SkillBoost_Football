package com.skillboostfootball.backend_main_springboot.application.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;
    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final AccessDeniedHandlerImpl accessDeniedHandler;

    //Define endpoints públicos para operaciones de lectura (GET)
    private static final String[] PUBLIC_READ_ENDPOINTS = {
        "/api/main/entrenadores", "/api/main/entrenamientos", "/api/main/entrenamientos/{slug}", "/api/main/entrenamientos/filter-data",
        "/api/main/entrenamientos/count", "/api/main/pistas/{pistaSlug}/horarios-ocupados", "/api/main/pistas", "/api/main/pistas/{slug}",
        "/api/main/profiles/{numeroSocio}", "/api/main/profiles/entrenadores/{numeroEntrenador}", "/api/main/subtipo-tecnificaciones",
        "/api/main/subtipo-tecnificaciones/{slug}", "/api/main/tecnificaciones/{tecnificacionSlug}/subtipos", "/api/main/subtipo-tecnificaciones-scroll",
        "/api/main/suscripciones", "/api/main/tecnificaciones", "/api/main/tecnificaciones/{slug}", "/api/main/organizaciones", "/api/main/organizaciones/{slug}"
    };

    //Define endpoints públicos para operaciones de escritura (POST, PUT, etc.)
    private static final String[] PUBLIC_WRITE_ENDPOINTS = {
        "/api/main/usuarios", "/api/main/login", "/api/main/refresh", "/api/main/logout"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, PUBLIC_WRITE_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_READ_ENDPOINTS).permitAll()
                .anyRequest().authenticated()
            )
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
