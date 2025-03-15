package com.skillboostfootball.backend_main_springboot.domain.repositories.logros;

import com.skillboostfootball.backend_main_springboot.domain.entities.logros.Logro;
import java.util.List;
import java.util.Optional;

public interface LogroRepository {

    List<Logro> findAll();
    Optional<Logro> findById(Long id);
    List<Logro> findByRequisitoEntrenamientos(Integer requisitoEntrenamientos);
    List<Logro> findLogrosOrdenadosPorRequisito();
    
}
