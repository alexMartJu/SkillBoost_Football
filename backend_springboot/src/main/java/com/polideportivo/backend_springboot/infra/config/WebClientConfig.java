package com.polideportivo.backend_springboot.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient.Builder webClientBuilder() {
        // Configurar tiempo de espera para conexión y lectura
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(30))  // Timeout de lectura (respuesta)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);  // Timeout de conexión (5 segundos)

        // Crear WebClient con el HttpClient configurado
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}
