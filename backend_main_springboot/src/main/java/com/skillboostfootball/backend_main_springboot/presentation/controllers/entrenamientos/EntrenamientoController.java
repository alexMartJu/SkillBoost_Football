package com.skillboostfootball.backend_main_springboot.presentation.controllers.entrenamientos;

import com.skillboostfootball.backend_main_springboot.application.security.authorization.CheckSecurity;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.CountEntrenamientosWithFiltersUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientoBySlugUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientoFilterDataUseCase;
import com.skillboostfootball.backend_main_springboot.application.useCases.entrenamientos.GetEntrenamientosWithFiltersUseCase;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.request.EntrenamientoFilterRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoCountResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoFilterDataResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response.EntrenamientoWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.entrenamientos.EntrenamientoAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class EntrenamientoController {
    private final GetEntrenamientosWithFiltersUseCase getEntrenamientosWithFiltersUseCase;
    private final GetEntrenamientoBySlugUseCase getEntrenamientoBySlugUseCase;
    private final GetEntrenamientoFilterDataUseCase getEntrenamientoFilterDataUseCase;
    private final CountEntrenamientosWithFiltersUseCase countEntrenamientosWithFiltersUseCase;
    private final EntrenamientoAssembler assembler;
    
    //Listar los entrenamientos con filtros
    @GetMapping("/entrenamientos")
    @CheckSecurity.Public.canRead
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

    // Listar un entrenamiento por slug
    @GetMapping("/entrenamientos/{slug}")
    @CheckSecurity.Public.canRead
    public EntrenamientoResponse getEntrenamientoBySlug(@PathVariable String slug) {
        var entrenamiento = getEntrenamientoBySlugUseCase.execute(slug);
        return assembler.toResponse(entrenamiento);
    }

    // Obtener los datos de los entrenamientos para filtros
    @GetMapping("/entrenamientos/filter-data")
    @CheckSecurity.Public.canRead
    public EntrenamientoFilterDataResponse getFilterData() {
        var filterData = getEntrenamientoFilterDataUseCase.execute();
        
        return EntrenamientoFilterDataResponse.builder()
            .totalEntrenamientos(filterData.totalEntrenamientos())
            .plazasMinimas(filterData.plazasMinimas())
            .plazasMaximas(filterData.plazasMaximas())
            .build();
    }

    @GetMapping("/entrenamientos/count")
    @CheckSecurity.Public.canRead
    public EntrenamientoCountResponse countEntrenamientosWithFilters(EntrenamientoFilterRequest filter) {
        Long count = countEntrenamientosWithFiltersUseCase.execute(
            filter.getNombre(),
            filter.getNivel(),
            filter.getEdadMinima(),
            filter.getEdadMaxima(),
            filter.getMaxPlazasMin(),
            filter.getMaxPlazasMax(),
            filter.getTecnificacionNombre(),
            filter.getFechaInicio(),
            filter.getFechaFin()
        );
        
        return EntrenamientoCountResponse.builder()
            .count(count)
            .build();
    }

}
