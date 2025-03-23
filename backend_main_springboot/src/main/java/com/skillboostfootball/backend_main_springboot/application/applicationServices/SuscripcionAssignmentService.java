package com.skillboostfootball.backend_main_springboot.application.applicationServices;


import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones.ProfileSuscripcion;
import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.suscripciones.SuscripcionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profileSuscripciones.ProfileSuscripcionRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones.SuscripcionRepository;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SuscripcionAssignmentService {
    
    private static final Logger logger = LoggerFactory.getLogger(SuscripcionAssignmentService.class);
    
    private final SuscripcionRepository suscripcionRepository;
    private final ProfileSuscripcionRepository profileSuscripcionRepository;
    private final ProfileRepository profileRepository;
    
    private static final String ELITE_PROFESIONAL_SLUG = "elite-profesional";
    private static final String DESARROLLO_AVANZADO_SLUG = "desarrollo-avanzado";
    
    @Transactional
    public void asignarSuscripcionJugadorClub(Profile profile) {
        logger.info("Asignando suscripción Elite Profesional al jugador de club con ID: {}", profile.getId());
        asignarSuscripcion(profile, ELITE_PROFESIONAL_SLUG);
    }
    
    @Transactional
    public void asignarSuscripcionJugadorSocial(Profile profile) {
        logger.info("Asignando suscripción Desarrollo Avanzado al jugador social con ID: {}", profile.getId());
        asignarSuscripcion(profile, DESARROLLO_AVANZADO_SLUG);
    }
    
    private void asignarSuscripcion(Profile profile, String slugSuscripcion) {
        //Buscar la suscripción por slug
        Suscripcion suscripcion = suscripcionRepository.findBySlug(slugSuscripcion).orElseThrow(() -> new SuscripcionNotFoundException());
        
        logger.info("Suscripción encontrada: {}, con {} entrenamientos", suscripcion.getNombre(), suscripcion.getEntrenamientosTotales());
        
        //Crear la relación entre perfil y suscripción
        ProfileSuscripcion profileSuscripcion = ProfileSuscripcion.builder()
            .profileId(profile.getId())
            .suscripcionId(suscripcion.getId())
            .fechaInicio(LocalDate.now())
            .fechaFin(LocalDate.now().plusMonths(1)) //Suscripción por un mes
            .status("active")
            .metodoPago("stripe")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        //Guardar la relación
        profileSuscripcion = profileSuscripcionRepository.save(profileSuscripcion);
        logger.info("Suscripción asignada con ID: {}", profileSuscripcion.getId());
        
        //Actualizar los entrenamientos disponibles en el perfil
        Integer entrenamientosActuales = profile.getEntrenamientosDisponibles() != null ? profile.getEntrenamientosDisponibles() : 0;
        profile.agregarEntrenamientosDisponibles(suscripcion.getEntrenamientosTotales());
        logger.info("Entrenamientos actualizados: {} → {}", entrenamientosActuales, profile.getEntrenamientosDisponibles());
        
        //Guardar el perfil actualizado
        profileRepository.save(profile);
    }
}
