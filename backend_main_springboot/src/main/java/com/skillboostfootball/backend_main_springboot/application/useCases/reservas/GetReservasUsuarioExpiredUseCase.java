package com.skillboostfootball.backend_main_springboot.application.useCases.reservas;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.profiles.Profile;
import com.skillboostfootball.backend_main_springboot.domain.entities.reservas.Reserva;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.repositories.reservas.ReservaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetReservasUsuarioExpiredUseCase {
    
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final ReservaRepository reservaRepository;
    
    @Transactional(readOnly = true)
    public List<Reserva> execute() {
        
        Usuario currentUser = getCurrentUserUseCase.execute();
        Profile profile = currentUser.getProfile();
        
        return reservaRepository.findByProfileIdAndEntrenamientoStatus(profile.getId(), "expired");
        
    }
}
