package com.skillboostfootball.backend_main_springboot.domain.repositories.subtiposTecnificacion;

import com.skillboostfootball.backend_main_springboot.domain.entities.subtiposTecnificacion.SubtipoTecnificacion;
import java.util.List;
import java.util.Optional;

public interface SubtipoTecnificacionRepository {
    List<SubtipoTecnificacion> findAllActive();
    Optional<SubtipoTecnificacion> findBySlug(String slug);
    SubtipoTecnificacion save(SubtipoTecnificacion subtipoTecnificacion);
    void delete(SubtipoTecnificacion subtipoTecnificacion);
}
