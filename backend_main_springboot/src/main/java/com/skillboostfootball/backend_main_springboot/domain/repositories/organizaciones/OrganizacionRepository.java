package com.skillboostfootball.backend_main_springboot.domain.repositories.organizaciones;

import com.skillboostfootball.backend_main_springboot.domain.entities.organizaciones.Organizacion;
import java.util.List;
import java.util.Optional;

public interface OrganizacionRepository {

    List<Organizacion> findAllActive();
    Optional<Organizacion> findBySlug(String slug);
    Organizacion save(Organizacion organizacion);
    void delete(Organizacion organizacion);
    boolean existsByNombre(String nombre);

}
