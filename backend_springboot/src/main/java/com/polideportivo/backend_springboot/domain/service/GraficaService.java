package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Grafica;

import java.util.List;

public interface GraficaService {
    
    List<Grafica> getGraficasByAuthenticatedUserAndAnio(Integer año);
    
}
