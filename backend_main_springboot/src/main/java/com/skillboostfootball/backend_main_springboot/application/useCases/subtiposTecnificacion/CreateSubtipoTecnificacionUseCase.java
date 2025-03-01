package com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateSubtipoTecnificacionUseCase {
    
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    private final TecnificacionRepository tecnificacionRepository;
    private final SlugService slugService;
    
    @Transactional
    public SubtipoTecnificacion execute(String tecnificacionSlug, String nombre, String descripcion) {
        //Buscar la tecnificación a la que pertenecerá el subtipo
        Tecnificacion tecnificacion = tecnificacionRepository.findBySlug(tecnificacionSlug).orElseThrow(TecnificacionNotFoundException::new);
        
        String slug = slugService.generateSlug(nombre);
        
        SubtipoTecnificacion subtipo = SubtipoTecnificacion.builder()
            .nombre(nombre)
            .descripcion(descripcion)
            .slug(slug)
            .tecnificacion(tecnificacion)
            .images(new ArrayList<>())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        
        return subtipoTecnificacionRepository.save(subtipo);
    }
}
