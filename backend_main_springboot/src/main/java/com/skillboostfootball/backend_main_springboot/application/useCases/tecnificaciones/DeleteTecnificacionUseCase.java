package com.skillboostfootball.backend_main_springboot.application.useCases.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.tecnificaciones.TecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones.TecnificacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTecnificacionUseCase {
    private final TecnificacionRepository tecnificacionRepository;
    
    @Transactional
    public void execute(String slug) {
        // Buscar la tecnificación por slug
        Tecnificacion tecnificacion = tecnificacionRepository.findBySlug(slug).orElseThrow(TecnificacionNotFoundException::new);
        
        // Marcar como eliminada (soft delete)
        tecnificacion.delete();
        
        // Guardar cambios
        tecnificacionRepository.delete(tecnificacion);
    }
}
