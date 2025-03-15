package com.skillboostfootball.backend_main_springboot.domain.repositories.graficas;

import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import java.util.List;
import java.util.Optional;

public interface GraficaRepository {

    List<Grafica> findByProfileNumeroSocioAndAño(String numeroSocio, Integer año);
    Optional<Grafica> findByProfileIdAndSeccionAndMesAndAño(Long profileId, String seccion, Integer mes, Integer año);
    Grafica save(Grafica grafica);
    
}
