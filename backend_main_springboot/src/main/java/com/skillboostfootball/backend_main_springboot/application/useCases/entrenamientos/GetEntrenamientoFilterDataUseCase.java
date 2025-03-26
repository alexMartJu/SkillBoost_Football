package com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos;

import com.skillboostfootball.backend_main_springboot.domain.repositories.entrenamientos.EntrenamientoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetEntrenamientoFilterDataUseCase {
    
    private final EntrenamientoRepository entrenamientoRepository;
    
    @Transactional(readOnly = true)
    public FilterData execute() {
        Long count = entrenamientoRepository.countActiveEntrenamientos();
        Integer minPlazas = entrenamientoRepository.findMinPlazas();
        Integer maxPlazas = entrenamientoRepository.findMaxPlazas();
        
        return new FilterData(count, minPlazas, maxPlazas);
    }
    
    public record FilterData(Long totalEntrenamientos, Integer plazasMinimas, Integer plazasMaximas) {}
    
}
