package com.skillboostfootball.backend_main_springboot.domain.repositories.suscripciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;
import java.util.List;
import java.util.Optional;

public interface SuscripcionRepository {

    List<Suscripcion> findAll();
    Optional<Suscripcion> findBySlug(String slug);
    Suscripcion save(Suscripcion suscripcion);
    Optional<Suscripcion> findById(Long id);

}
