package com.skillboostfootball.backend_main_springboot.domain.repositories.incidencias;

import com.skillboostfootball.backend_main_springboot.domain.entities.incidencias.Incidencia;

public interface IncidenciaRepository {
    Incidencia save(Incidencia incidencia);
}
