package com.skillboostfootball.backend_main_springboot.application.useCases.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.application.applicationServices.SlugService;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.subtiposTecnificacion.SubtipoTecnificacionNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion.SubtipoTecnificacionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateSubtipoTecnificacionUseCase {
    
    private final SubtipoTecnificacionRepository subtipoTecnificacionRepository;
    private final SlugService slugService;
    
    @Transactional
    @CacheEvict(value = {"subtiposTecnificacion", "tecnificaciones"}, allEntries = true)
    public SubtipoTecnificacion execute(String slug, String nombre, String descripcion, String tipoEntrenamiento, String objetivos, 
        String beneficios, String frecuenciaSugerida, String metodoEvaluacion, String tecnologiaUtilizada) {
            
        //Buscar el subtipo por slug
        SubtipoTecnificacion subtipo = subtipoTecnificacionRepository.findBySlug(slug).orElseThrow(SubtipoTecnificacionNotFoundException::new);
        
        //Generar nuevo slug si el nombre cambió
        String newSlug = slug;
        if (!subtipo.getNombre().equals(nombre)) {
            newSlug = slugService.generateSlug(nombre);
        }
        
        //Actualizar el subtipo
        subtipo.update(nombre, descripcion, newSlug, tipoEntrenamiento, objetivos, beneficios, 
            frecuenciaSugerida, metodoEvaluacion, tecnologiaUtilizada);
        
        //Guardar cambios
        return subtipoTecnificacionRepository.save(subtipo);
    }
}
