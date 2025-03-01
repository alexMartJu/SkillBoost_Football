package com.skillboostfootball.backend_main_springboot.application.useCases.pistas;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.pistas.PistaNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import com.skillboostfootball.backend_main_springboot.domain.repositories.pistas.PistaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdatePistaUseCase {
    
    private final PistaRepository pistaRepository;
    private final SlugService slugService;
    
    @Transactional
    public Pista execute(String slug, String nombre, String descripcion, String tipo, String dimensiones, Integer capacidad) {
        //Buscar la pista por slug
        Pista pista = pistaRepository.findBySlug(slug).orElseThrow(PistaNotFoundException::new);
        
        //Generar un nuevo slug si el nombre cambió
        String newSlug = slug;
        if (!pista.getNombre().equals(nombre)) {
            newSlug = slugService.generateSlug(nombre);
        }
        
        //Actualizar la pista
        pista.update(nombre, descripcion, newSlug, tipo, dimensiones, capacidad);
        
        return pistaRepository.save(pista);
    }

}
