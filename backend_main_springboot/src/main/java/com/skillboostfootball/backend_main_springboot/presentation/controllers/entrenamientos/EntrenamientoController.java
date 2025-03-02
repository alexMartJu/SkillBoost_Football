package com.skillboostfootball.backend_main_springboot.presentation.controllers.entrenamientos;

import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientosWithFiltersUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.request.EntrenamientoFilterRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenamientos.EntrenamientoAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EntrenamientoController {
    private final GetEntrenamientosWithFiltersUseCase getEntrenamientosWithFiltersUseCase;
    private final EntrenamientoAssembler assembler;
    
    @GetMapping("/entrenamientos")
    public EntrenamientoWrapper getEntrenamientosWithFilters(
            EntrenamientoFilterRequest filter,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "3") int limit) {
        
        Pageable pageable = PageRequest.of(offset, limit);
        
        var entrenamientosPage = getEntrenamientosWithFiltersUseCase.execute(
            filter.getNombre(),
            filter.getNivel(),
            filter.getEdadMinima(),
            filter.getEdadMaxima(),
            filter.getMaxPlazasMin(),
            filter.getMaxPlazasMax(),
            filter.getTecnificacionNombre(),
            filter.getFechaInicio(),
            filter.getFechaFin(),
            pageable
        );
        
        return assembler.toWrapper(entrenamientosPage);
    }
}
