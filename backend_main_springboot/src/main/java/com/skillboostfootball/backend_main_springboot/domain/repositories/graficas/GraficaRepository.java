package com.skillboostfootball.backend_main_springboot.domain.repositories.graficas;

import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import java.util.List;

public interface GraficaRepository {

    List<Grafica> findByProfileNumeroSocioAndAño(String numeroSocio, Integer año);
    
}
