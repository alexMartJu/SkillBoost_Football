package com.skillboostfootball.backend_main_springboot.application.useCases.pistas;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;
import org.springframework.cache.annotation.CacheEvict;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreatePistaUseCase {
    
    private final PistaRepository pistaRepository;
    private final SlugService slugService;
    
    @Transactional
    @CacheEvict(value = "pistas", allEntries = true)
    public Pista execute(String nombre, String descripcion, String tipo, String dimensiones, Integer capacidad) {
        String slug = slugService.generateSlug(nombre);
        
        Pista pista = Pista.builder()
            .nombre(nombre)
            .descripcion(descripcion)
            .slug(slug)
            .tipo(tipo)
            .dimensiones(dimensiones)
            .capacidad(capacidad)
            .images(new ArrayList<>())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();       
        return pistaRepository.save(pista);
    }

}
