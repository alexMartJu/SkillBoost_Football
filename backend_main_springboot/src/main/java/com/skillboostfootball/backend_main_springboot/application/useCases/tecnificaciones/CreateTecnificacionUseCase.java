package com.skillboostfootball.backend_main_springboot.application.useCases.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateTecnificacionUseCase {
    private final TecnificacionRepository tecnificacionRepository;
    private final SlugService slugService;
    
    @Transactional
    public Tecnificacion execute(String nombre, String descripcion) {
        String slug = slugService.generateSlug(nombre);
        
        Tecnificacion tecnificacion = Tecnificacion.builder()
            .nombre(nombre)
            .descripcion(descripcion)
            .slug(slug)
            .subtipos(new ArrayList<>())
            .images(new ArrayList<>())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        return tecnificacionRepository.save(tecnificacion);
    }
}
