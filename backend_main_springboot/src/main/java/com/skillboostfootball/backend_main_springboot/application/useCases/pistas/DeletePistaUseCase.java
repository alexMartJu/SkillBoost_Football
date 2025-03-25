package com.skillboostfootball.backend_main_springboot.application.useCases.pistas;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas.PistaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeletePistaUseCase {
    
    private final PistaRepository pistaRepository;
    
    @Transactional
    @CacheEvict(value = "pistas", allEntries = true)
    public void execute(String slug) {
        Pista pista = pistaRepository.findBySlug(slug).orElseThrow(PistaNotFoundException::new);
        
        // Marcar como eliminada (soft delete) a través del método de domain
        pista.delete();
        
        pistaRepository.delete(pista);
    }

}
