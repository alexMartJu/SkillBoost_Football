package com.skillboostfootball.backend_main_springboot.application.useCases.reservas;

import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientoBySlugUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.AgeNotAllowedException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.entrenamientos.EntrenamientoFullException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.profiles.NoAvailableTrainingsException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.reservas.ReservaAlreadyExistsException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.reservas.ReservaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InscribirseEntrenamientoUseCase {
    
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final GetEntrenamientoBySlugUseCase getEntrenamientoBySlugUseCase;
    private final ReservaRepository reservaRepository;
    private final ProfileRepository profileRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    
    @Transactional
    public Reserva execute(String entrenamientoSlug) {

        Usuario currentUser = getCurrentUserUseCase.execute();
        Profile profile = currentUser.getProfile();
        
        //Validar que el usuario tiene entrenamientos disponibles
        if (!profile.puedeApuntarseAEntrenamiento()) {
            throw new NoAvailableTrainingsException();
        }
        Entrenamiento entrenamiento = getEntrenamientoBySlugUseCase.execute(entrenamientoSlug);
        
        //Validar que el entrenamiento no está completo
        int inscripcionesActuales = reservaRepository.countByEntrenamientoId(entrenamiento.getId());
        if (inscripcionesActuales >= entrenamiento.getMaxPlazas()) {
            throw new EntrenamientoFullException();
        }
        
        //Validar que el usuario no se haya inscrito previamente
        if (reservaRepository.findByProfileIdAndEntrenamientoId(profile.getId(), entrenamiento.getId()).isPresent()) {
            throw new ReservaAlreadyExistsException();
        }
        
        //Validar que la edad del usuario está dentro del rango permitido
        Integer edad = profile.getEdad();
        if (edad != null && (edad < entrenamiento.getEdadMinima() || edad > entrenamiento.getEdadMaxima())) {
            throw new AgeNotAllowedException();
        }
        
        //Crear la reserva
        Reserva reserva = Reserva.builder()
            .profile(profile)
            .entrenamiento(entrenamiento)
            .fecha(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        //Guardar reserva
        reserva = reservaRepository.save(reserva);
        
        //Decrementar los entrenamientos disponibles del perfil
        profile.decrementarEntrenamientoDisponible();
        profileRepository.save(profile);
        
        //Si esta inscripción ha completado el entrenamiento, actualizar el estado
        if (inscripcionesActuales + 1 >= entrenamiento.getMaxPlazas()) {
            entrenamiento.setStatus("completed");
            entrenamientoRepository.save(entrenamiento);
        }
        return reserva;
        
    }
}
