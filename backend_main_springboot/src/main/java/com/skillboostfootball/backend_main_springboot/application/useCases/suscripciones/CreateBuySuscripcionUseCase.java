package com.skillboostfootball.backend_main_springboot.application.useCases.suscripciones;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.pagos.Pago;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;
import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.suscripciones.SuscripcionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pagos.PagoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones.SuscripcionRepository;
import com.skillboostfootball.backend_main_springboot.application.applicationServices.NotificationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateBuySuscripcionUseCase {
    
    private static final Logger logger = LoggerFactory.getLogger(CreateBuySuscripcionUseCase.class);
    
    private final ProfileSuscripcionRepository profileSuscripcionRepository;
    private final SuscripcionRepository suscripcionRepository;
    private final PagoRepository pagoRepository;
    private final ProfileRepository profileRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final NotificationService notificationService;

    @Transactional
    public Pago execute(String suscripcionSlug, BigDecimal monto, String metodoPago, String referenciaExterna) {

        //Obtener usuario y perfil actual
        Usuario usuario = getCurrentUserUseCase.execute();
        Profile profile = usuario.getProfile();
        
        //Obtener la suscripción
        Suscripcion suscripcion = suscripcionRepository.findBySlug(suscripcionSlug).orElseThrow(SuscripcionNotFoundException::new);
        
        //Verificar si ya existe una suscripción activa
        Optional<ProfileSuscripcion> suscripcionActivaOpt = profileSuscripcionRepository.findActiveByProfileId(profile.getId());
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = fechaInicio.plusMonths(1);
        
        //Si hay una suscripción activa, la marcamos como cancelada y tomamos su fecha fin
        if (suscripcionActivaOpt.isPresent()) {
            ProfileSuscripcion suscripcionActiva = suscripcionActivaOpt.get();
            if (suscripcionActiva.getFechaFin().isAfter(fechaInicio)) {
                fechaFin = suscripcionActiva.getFechaFin().plusMonths(1); // Extendemos un mes más desde la fecha fin anterior
            }
            suscripcionActiva.cancelar();
            profileSuscripcionRepository.save(suscripcionActiva);
        }
        
        //Crear nueva suscripción
        ProfileSuscripcion nuevaSuscripcion = ProfileSuscripcion.builder()
                .profileId(profile.getId())
                .suscripcionId(suscripcion.getId())
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .status("pending") // Inicialmente pendiente hasta confirmar pago
                .metodoPago(metodoPago)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        nuevaSuscripcion = profileSuscripcionRepository.save(nuevaSuscripcion);
        
        //Crear registro de pago
        Pago pago = Pago.builder()
                .profileSuscripcionId(nuevaSuscripcion.getId())
                .monto(monto)
                .metodoPago(metodoPago)
                .status("pending")
                .fecha(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        pago = pagoRepository.save(pago);
        
        pago.completar(referenciaExterna);
        pago = pagoRepository.save(pago);
        
        //Activar la suscripción
        nuevaSuscripcion.activar(pago.getId());
        profileSuscripcionRepository.save(nuevaSuscripcion);
        
        //Actualizar entrenamientos disponibles del perfil
        Integer entrenamientosActuales = profile.getEntrenamientosDisponibles() != null ? profile.getEntrenamientosDisponibles() : 0;
        profile.agregarEntrenamientosDisponibles(suscripcion.getEntrenamientosTotales());
        logger.info("Entrenamientos actualizados: {} → {}", entrenamientosActuales, profile.getEntrenamientosDisponibles());
        
        profileRepository.save(profile);

        notificationService.sendSubscriptionPaymentNotification(usuario, suscripcion.getId(), suscripcion.getNombre(), monto, metodoPago
        ).subscribe(
            null,
            error -> logger.error("Error al procesar notificación de pago: {}", error.getMessage(), error)
        );
        
        return pago;
    }
}
