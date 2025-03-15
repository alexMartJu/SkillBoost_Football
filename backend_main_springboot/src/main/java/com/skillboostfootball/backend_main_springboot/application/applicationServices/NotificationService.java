package com.skillboostfootball.backend_main_springboot.application.applicationServices;

import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.logros.Logro;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final WebClient webClient;

    public NotificationService(WebClient.Builder webClientBuilder, @Value("${notification.service.url}") String notificationServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(notificationServiceUrl).build();
    }

    public Mono<Void> sendUserRegistrationNotification(Usuario usuario) {

        logger.info("Enviando notificación de registro para el usuario: {}", usuario.getEmail());

        Map<String, Object> requestBody = new HashMap<>();
        // Convertir el ID a String para evitar problemas con BigInt
        requestBody.put("userId", usuario.getId().toString());
        requestBody.put("email", usuario.getEmail());
        requestBody.put("nombre", usuario.getProfile().getNombre());

        return webClient.post()
                .uri("/api/user/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> logger.info("Notificación de registro enviada exitosamente para: {}",
                        usuario.getEmail()))
                .onErrorResume(e -> {
                    logger.error("Error al enviar notificación de registro: {}", e.getMessage(), e);
                    return Mono.empty();
                })
                .then();
    }

    public Mono<Void> sendSubscriptionPaymentNotification(Usuario usuario, Long suscripcionId, String nombreSuscripcion, BigDecimal monto, String metodoPago) {

        logger.info("Enviando notificación de pago para el usuario: {}, suscripción: {}", usuario.getEmail(), nombreSuscripcion);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", usuario.getId());
        requestBody.put("email", usuario.getEmail());
        requestBody.put("suscripcionId", suscripcionId);
        requestBody.put("nombreSuscripcion", nombreSuscripcion);
        requestBody.put("monto", monto);
        requestBody.put("metodoPago", metodoPago);

        return webClient.post()
                .uri("/api/subscription/payment")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> logger.info("Notificación de pago enviada exitosamente para: {}",
                        usuario.getEmail()))
                .onErrorResume(e -> {
                    logger.error("Error al enviar notificación de pago: {}", e.getMessage(), e);
                    return Mono.empty();
                })
                .then();
    }

    public Mono<Void> sendTrainingRegistrationNotification(Usuario usuario, Entrenamiento entrenamiento) {
        
        logger.info("Enviando notificación de inscripción a entrenamiento para el usuario: {}, entrenamiento: {}",
                usuario.getEmail(), entrenamiento.getNombre());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", usuario.getId().toString());
        requestBody.put("entrenamientoId", entrenamiento.getId().toString());
        requestBody.put("nombreEntrenamiento", entrenamiento.getNombre());

        return webClient.post()
                .uri("/api/training/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> logger.info(
                        "Notificación de inscripción a entrenamiento enviada exitosamente para: {}",
                        usuario.getEmail()))
                .onErrorResume(e -> {
                    logger.error("Error al enviar notificación de inscripción a entrenamiento: {}", e.getMessage(), e);
                    return Mono.empty();
                })
                .then();
    }

    public Mono<Void> sendAchievementNotification(Usuario usuario, Logro logro) {
        logger.info("Enviando notificación de logro para el usuario: {}, logro: {}", 
                usuario.getEmail(), logro.getNombre());

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userId", usuario.getId().toString());
        requestBody.put("email", usuario.getEmail());
        requestBody.put("logroId", logro.getId().toString());
        requestBody.put("nombreLogro", logro.getNombre());
        requestBody.put("requisitoEntrenamientos", logro.getRequisitoEntrenamientos());
        requestBody.put("recompensa", logro.getRecompensa());

        return webClient.post()
                .uri("/api/achievement/unlocked")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .toBodilessEntity()
                .doOnSuccess(response -> logger.info("Notificación de logro enviada exitosamente para: {}",
                        usuario.getEmail()))
                .onErrorResume(e -> {
                    logger.error("Error al enviar notificación de logro: {}", e.getMessage(), e);
                    return Mono.empty();
                })
                .then();
    }
}
