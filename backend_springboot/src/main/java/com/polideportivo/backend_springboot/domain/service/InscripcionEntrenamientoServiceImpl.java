package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamiento;
import com.polideportivo.backend_springboot.domain.model.InscripcionEntrenamientoId;
import com.polideportivo.backend_springboot.domain.model.Profile;
import com.polideportivo.backend_springboot.domain.model.Entrenamiento;
import com.polideportivo.backend_springboot.domain.repository.InscripcionEntrenamientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.polideportivo.backend_springboot.domain.exception.InscripcionAlreadyExistsException;
import com.polideportivo.backend_springboot.domain.exception.InscripcionNotFoundException;
import com.polideportivo.backend_springboot.domain.exception.NoAvailableSlotsException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InscripcionEntrenamientoServiceImpl implements InscripcionEntrenamientoService {
    
    private final InscripcionEntrenamientoRepository inscripcionRepository;
    private final UsuarioService usuarioService;
    private final EntrenamientoService entrenamientoService;

    @Transactional
    @Override
    public InscripcionEntrenamiento inscribir(String slug) {
        // Obtener el perfil del usuario actual y el entrenamiento
        Profile profile = usuarioService.getCurrentUser().getProfile();
        Entrenamiento entrenamiento = entrenamientoService.getEntrenamientoBySlug(slug);

        // Verificar si el entrenamiento tiene plazas disponibles
        long inscripcionesActuales = inscripcionRepository.countByEntrenamientoId(entrenamiento.getId());
        if (inscripcionesActuales >= entrenamiento.getMaxPlazas()) {
            throw new NoAvailableSlotsException();
        }

        // Crear la clave compuesta
        InscripcionEntrenamientoId id = new InscripcionEntrenamientoId(entrenamiento.getId(), profile.getId());

        // Validar si ya existe la inscripción
        if (inscripcionRepository.findById(id).isPresent()) {
            // Lanzar excepción si ya está inscrito
            throw new InscripcionAlreadyExistsException("El usuario ya está inscrito en este entrenamiento.");
        }

        // Crear la inscripción
        InscripcionEntrenamiento inscripcion = InscripcionEntrenamiento.builder()
            .id(id)
            .entrenamiento(entrenamiento)
            .profile(profile)
            .createdAt(LocalDateTime.now())
            .build();

        // Guardar la inscripción en el repositorio
        InscripcionEntrenamiento nuevaInscripcion = inscripcionRepository.save(inscripcion);

        // Verificar si después de esta inscripción se alcanzan las plazas máximas
        inscripcionesActuales += 1; // Incrementamos por la nueva inscripción
        if (inscripcionesActuales >= entrenamiento.getMaxPlazas()) {
            entrenamiento.setStatus("completed");
            entrenamientoService.update(entrenamiento);
        }

        return nuevaInscripcion;
    }

    @Transactional
    @Override
    public String desinscribir(String slug) {
        // Obtener el perfil del usuario actual y el entrenamiento
        Profile profile = usuarioService.getCurrentUser().getProfile();
        Entrenamiento entrenamiento = entrenamientoService.getEntrenamientoBySlug(slug);

        // Crear la clave compuesta
        InscripcionEntrenamientoId id = new InscripcionEntrenamientoId(entrenamiento.getId(), profile.getId());

        // Verificar si existe la inscripción
        if (!inscripcionRepository.findById(id).isPresent()) {
            throw new InscripcionNotFoundException();
        }

        // Eliminar la inscripción
        inscripcionRepository.deleteById(id);

        // Verificar si el entrenamiento estaba lleno y ahora tiene plazas disponibles
        long inscripcionesActuales = inscripcionRepository.countByEntrenamientoId(entrenamiento.getId());
        if (entrenamiento.getStatus().equals("completed") && inscripcionesActuales < entrenamiento.getMaxPlazas()) {
            entrenamiento.setStatus("accepted");
            entrenamientoService.update(entrenamiento);
        }

        return profile.getNumeroSocio();
    }
}
