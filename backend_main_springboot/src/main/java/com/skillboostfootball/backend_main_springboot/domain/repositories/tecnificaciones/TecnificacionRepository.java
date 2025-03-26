package com.skillboostfootball.backend_main_springboot.domain.repositories.tecnificaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.tecnificaciones.Tecnificacion;
import java.util.List;
import java.util.Optional;

public interface TecnificacionRepository {
    List<Tecnificacion> findAllActive();
    Optional<Tecnificacion> findBySlug(String slug);
    Tecnificacion save(Tecnificacion tecnificacion);
    void delete(Tecnificacion tecnificacion);
}
