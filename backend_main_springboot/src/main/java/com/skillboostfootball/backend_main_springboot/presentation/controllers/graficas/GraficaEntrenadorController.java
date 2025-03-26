package com.skillboostfootball.backend_main_springboot.presentation.controllers.graficas;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.graficas.GetGraficasByNumeroSocioAndYearUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.graficas.UpdateGraficaByEntrenadorUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.graficas.UpdateGraficaByEntrenadorUseCase.GraficaUpdateData;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.graficas.GraficaAssembler;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.request.GraficaUpdateRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.response.GraficaWrapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class GraficaEntrenadorController {
    
    private final UpdateGraficaByEntrenadorUseCase updateGraficaByEntrenadorUseCase;
    private final GetGraficasByNumeroSocioAndYearUseCase getGraficasByNumeroSocioAndYearUseCase;
    private final GraficaAssembler graficaAssembler;
    
    //Actualizar gráficas de un jugador
    @PutMapping("/entrenador/profiles/{numeroSocio}/graficas")
    @CheckSecurity.Entrenador.canAccess
    public GraficaWrapper updateGraficas(@PathVariable String numeroSocio, @Valid @RequestBody GraficaUpdateRequest request) {
        
        var graficasData = request.getSecciones().stream()
                .map(seccion -> new GraficaUpdateData(seccion.getSeccion(), seccion.getNivel()))
                .collect(Collectors.toList());
        var graficas = updateGraficaByEntrenadorUseCase.execute(numeroSocio, graficasData);
        return graficaAssembler.toWrapper(graficas);
        
    }

    //Obtener las gráficas de un usuario por número de socio y año
    @GetMapping("/profiles/{numeroSocio}/graficas")
    @CheckSecurity.Entrenador.canAccess
    public GraficaWrapper getGraficasByNumeroSocioAndYear(
            @PathVariable String numeroSocio, 
            @RequestParam Integer año) {
        var graficas = getGraficasByNumeroSocioAndYearUseCase.execute(numeroSocio, año);
        return graficaAssembler.toWrapper(graficas);
    }
}
