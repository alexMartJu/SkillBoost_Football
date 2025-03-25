package com.skillboostfootball.backend_main_springboot.application.useCases.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTecnificacionUseCase {
    
    private final TecnificacionRepository tecnificacionRepository;
    private final SlugService slugService;
    
    @Transactional
    @CacheEvict(value = {"tecnificaciones", "subtiposTecnificacion"}, allEntries = true)
    public Tecnificacion execute(String slug, String nombre, String descripcion) {
        // Buscar la tecnificación por slug
        Tecnificacion tecnificacion = tecnificacionRepository.findBySlug(slug).orElseThrow(TecnificacionNotFoundException::new);
        
        // Generar nuevo slug si el nombre cambió
        String newSlug = slug;
        if (!tecnificacion.getNombre().equals(nombre)) {
            newSlug = slugService.generateSlug(nombre);
        }
        
        // Actualizar la tecnificación
        tecnificacion.update(nombre, descripcion, newSlug);
        
        // Guardar cambios
        return tecnificacionRepository.save(tecnificacion);
    }
}
