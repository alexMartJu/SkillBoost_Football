package com.polideportivo.backend_springboot.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class DynamicProxyFilter extends OncePerRequestFilter {

    private final WebClient.Builder webClientBuilder;
    private final String laravelBaseUrl;

    public DynamicProxyFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
        this.laravelBaseUrl = "http://laravel:3000/api/"; // URL base para Laravel
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String isSpringbootHeader = request.getHeader("isSpringboot");

        if ("false".equalsIgnoreCase(isSpringbootHeader)) {
            // Si el header es false, redirigimos la solicitud a Laravel
            forwardToLaravel(request, response);
        } else {
            // Si el header es true, seguimos con el flujo normal de Spring Boot
            filterChain.doFilter(request, response);
        }
    }

    private void forwardToLaravel(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Construir la solicitud dinámica para Laravel
            String laravelUri = laravelBaseUrl + request.getRequestURI();

            // Crear WebClient
            WebClient webClient = webClientBuilder.build();

            // Realizar la solicitud dinámica usando WebClient
            WebClient.RequestBodySpec requestSpec = webClient
                    .method(HttpMethod.valueOf(request.getMethod())) // Establecer el tipo de método (GET, POST, PUT, etc.)
                    .uri(laravelUri)
                    .headers(headers -> {
                        Enumeration<String> headerNames = request.getHeaderNames();
                        while (headerNames.hasMoreElements()) {
                            String headerName = headerNames.nextElement();
                            headers.add(headerName, request.getHeader(headerName));
                        }
                    });

            // Pasar el cuerpo de la solicitud (para POST/PUT)
            if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
                requestSpec.body(BodyInserters.fromValue(request.getInputStream().readAllBytes()));
            }

            // Ejecutar la solicitud y obtener la respuesta
            WebClient.ResponseSpec responseSpec = requestSpec.retrieve();

            // Transformar la respuesta de Laravel a la respuesta original
            responseSpec.toEntity(byte[].class).subscribe(responseEntity -> {
                response.setStatus(responseEntity.getStatusCode().value());
                responseEntity.getHeaders().forEach((key, value) -> response.setHeader(key, String.join(",", value)));
                try {
                    response.getOutputStream().write(responseEntity.getBody());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException("Error forwarding request to Laravel", e);
        }
    }
}
