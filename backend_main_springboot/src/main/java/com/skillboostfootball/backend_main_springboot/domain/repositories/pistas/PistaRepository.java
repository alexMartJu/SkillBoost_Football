package com.skillboostfootball.backend_main_springboot.domain.repositories.pistas;

import com.skillboostfootball.backend_main_springboot.domain.entities.pistas.Pista;
import java.util.List;
import java.util.Optional;

public interface PistaRepository {
    List<Pista> findAllActive();
    Optional<Pista> findBySlug(String slug);
    Pista save(Pista pista);
    void delete(Pista pista);
}
