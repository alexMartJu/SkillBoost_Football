package com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion.SubtipoTecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteSubtipoTecnificacionUseCase {
    
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    
    @Transactional
    @CacheEvict(value = {"subtiposTecnificacion", "tecnificaciones"}, allEntries = true)
    public void execute(String slug) {
        //Buscar el subtipo por slug
        SubtipoTecnificacion subtipo = subtipoTecnificacionRepository.findBySlug(slug).orElseThrow(SubtipoTecnificacionNotFoundException::new);
        
        //Marcar como eliminado (soft delete)
        subtipo.delete();
        
        //Guardar cambios
        subtipoTecnificacionRepository.delete(subtipo);
    }
}
