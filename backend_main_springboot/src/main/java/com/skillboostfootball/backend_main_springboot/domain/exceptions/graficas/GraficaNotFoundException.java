package com.skillboostfootball.backend_main_springboot.domain.exceptions.graficas;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.ResourceNotFoundException;

public class GraficaNotFoundException extends ResourceNotFoundException  {
    public GraficaNotFoundException() {
        super("No se encontraron gráficas para este usuario y año");
    }
}
