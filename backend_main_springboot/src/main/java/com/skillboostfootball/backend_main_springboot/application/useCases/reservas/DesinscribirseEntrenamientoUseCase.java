package com.skillboostfootball.backend_main_springboot.application.useCases.reservas;

import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientoBySlugUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.logros.VerifyLogrosUsuarioUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.entrenamientos.Entrenamiento;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.reservas.ReservaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.profiles.ProfileRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.reservas.ReservaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DesinscribirseEntrenamientoUseCase {
    
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final GetEntrenamientoBySlugUseCase getEntrenamientoBySlugUseCase;
    private final ReservaRepository reservaRepository;
    private final ProfileRepository profileRepository;
    private final EntrenamientoRepository entrenamientoRepository;
    private final VerifyLogrosUsuarioUseCase verificarLogrosUsuarioUseCase;
    
    @Transactional
    public void execute(String entrenamientoSlug) {
        //Obtener usuario actual
        Usuario currentUser = getCurrentUserUseCase.execute();
        Profile profile = currentUser.getProfile();
        
        Entrenamiento entrenamiento = getEntrenamientoBySlugUseCase.execute(entrenamientoSlug);
        
        //Comprobar que la reserva existe
        if (!reservaRepository.findByProfileIdAndEntrenamientoId(profile.getId(), entrenamiento.getId()).isPresent()) {
            throw new ReservaNotFoundException();
        }
        
        //Eliminar reserva
        reservaRepository.deleteByProfileIdAndEntrenamientoId(profile.getId(), entrenamiento.getId());
        //Incrementar los entrenamientos disponibles del perfil
        profile.agregarEntrenamientosDisponibles(1);
        profileRepository.save(profile);
        
        //Si el entrenamiento estaba completo, actualizarlo de nuevo a activo
        if ("completed".equals(entrenamiento.getStatus())) {
            entrenamiento.setStatus("active");
            entrenamientoRepository.save(entrenamiento);
        }

        //Verificar logros
        verificarLogrosUsuarioUseCase.execute(currentUser);

    }
}
