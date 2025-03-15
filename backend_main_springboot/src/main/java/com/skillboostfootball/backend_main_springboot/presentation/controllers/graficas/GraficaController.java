package com.skillboostfootball.backend_main_springboot.presentation.controllers.graficas;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.graficas.GetGraficasByCurrentUserAndYearUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.graficas.GraficaAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.response.GraficaWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GraficaController {
    
    private final GetGraficasByCurrentUserAndYearUseCase getGraficasByCurrentUserAndYearUseCase;
    private final GraficaAssembler graficaAssembler;
    
    //Obtener las gráficas del usuario actual por año
    @GetMapping("/profileGraficas")
    @CheckSecurity.Jugadores.canAccess
    public GraficaWrapper getGraficasByYear(@RequestParam Integer año) {
        var graficas = getGraficasByCurrentUserAndYearUseCase.execute(año);
        return graficaAssembler.toWrapper(graficas);
    }

}
