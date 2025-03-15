package com.skillboostfootball.backend_main_springboot.application.useCases.graficas;

import com.skillboostfootball.backend_main_springboot.application.useCases.usuarios.GetCurrentUserUseCase;
import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.graficas.GraficaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.graficas.GraficaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetGraficasByCurrentUserAndYearUseCase {
    private final GraficaRepository graficaRepository;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    
    @Transactional(readOnly = true)
    public List<Grafica> execute(Integer año) {
        
        Usuario currentUser = getCurrentUserUseCase.execute();
        if (currentUser.getProfile() == null) {
            throw new GraficaNotFoundException();
        }
        
        //Obtener el número de socio del usuario actual
        String numeroSocio = currentUser.getProfile().getNumeroSocio();

        //Obtener las gráficas por número de socio y año
        List<Grafica> graficas = graficaRepository.findByProfileNumeroSocioAndAño(numeroSocio, año);
        if (graficas.isEmpty()) {
            throw new GraficaNotFoundException();
        }
        
        return graficas;

    }
}
