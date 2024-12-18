package com.polideportivo.backend_springboot.api.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
        this.laravelBaseUrl = "http://laravel:3000"; // URL base para Laravel
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String isSpringbootHeader = request.getHeader("isSpringboot");

        if ("false".equalsIgnoreCase(isSpringbootHeader)) {
            // Si el header es false, redirigimos la solicitud a Laravel
            forwardToLaravel(request, response);
        } else if ("true".equalsIgnoreCase(isSpringbootHeader)) {
            // Si el header es true, seguimos con el flujo normal de Spring Boot
            filterChain.doFilter(request, response);
        } else {
            // Si el header es null o no válido, devolvemos el header recibido
            response.setContentType("text/plain");
            response.getWriter().write("Header 'isSpringboot': " + isSpringbootHeader);
            response.getWriter().write("\ngetRequestURI: " + request.getRequestURI());
        }
    }

    private void forwardToLaravel(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Construir la URI de Laravel
            String laravelUri = laravelBaseUrl + request.getRequestURI();
            logger.info("Forwarding request to Laravel: {}" + laravelUri);

            // Crear una instancia de WebClient con timeout configurado
            WebClient webClient = webClientBuilder.build();

            // Construir la solicitud dinámica
            WebClient.RequestBodySpec requestSpec = webClient
                    .method(HttpMethod.valueOf(request.getMethod())) // Establecer método HTTP
                    .uri(laravelUri)
                    .headers(headers -> copyRequestHeaders(request, headers));

            // Leer el cuerpo de la solicitud si es POST o PUT
            if ("POST".equalsIgnoreCase(request.getMethod()) || "PUT".equalsIgnoreCase(request.getMethod())) {
                requestSpec.body(BodyInserters.fromValue(request.getInputStream().readAllBytes()));
            }

            // Realizar la solicitud y manejar la respuesta
            WebClient.ResponseSpec responseSpec = requestSpec.retrieve();

            responseSpec.toEntity(byte[].class).blockOptional().ifPresentOrElse(responseEntity -> {
                // Establecer el código de estado
                response.setStatus(responseEntity.getStatusCode().value());

                // Copiar los encabezados de la respuesta de Laravel
                responseEntity.getHeaders().forEach((key, value) -> response.setHeader(key, String.join(",", value)));

                // Escribir el cuerpo de la respuesta
                try {
                    if (responseEntity.getBody() != null) {
                        response.getOutputStream().write(responseEntity.getBody());
                    }
                } catch (IOException e) {
                    logger.error("Error writing response body", e);
                }
            }, () -> {
                // En caso de que no haya respuesta (null), devolver un error 502
                logger.error("No response from Laravel");
                response.setStatus(HttpServletResponse.SC_BAD_GATEWAY);
                try {
                    response.getWriter().write("Error: No response from Laravel");
                } catch (IOException e) {
                    logger.error("Error writing error message", e);
                }
            });

        } catch (Exception e) {
            logger.error("Error forwarding request to Laravel", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("Error forwarding request to Laravel");
            } catch (IOException ex) {
                logger.error("Error writing error message", ex);
            }
        }
    }

    /**
     * Copiar encabezados de la solicitud HTTP original al proxy.
     */
    private void copyRequestHeaders(HttpServletRequest request, HttpHeaders headers) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (!"content-length".equalsIgnoreCase(headerName)) { // Excluir Content-Length
                Enumeration<String> values = request.getHeaders(headerName);
                while (values.hasMoreElements()) {
                    headers.add(headerName, values.nextElement());
                }
            }
        }
    }
}