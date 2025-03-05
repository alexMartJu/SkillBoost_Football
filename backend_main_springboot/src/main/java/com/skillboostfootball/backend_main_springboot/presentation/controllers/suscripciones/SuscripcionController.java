package com.skillboostfootball.backend_main_springboot.presentation.controllers.suscripciones;


import com.skillboostfootball.backend_main_springboot.application.useCases.suscripciones.*;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.request.UpdateSuscripcionPrecioRequest;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.response.SuscripcionResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.suscripciones.response.SuscripcionWrapper;
import com.skillboostfootball.backend_main_springboot.presentation.assemblers.suscripciones.SuscripcionAssembler;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SuscripcionController {

    private static final Logger logger = LoggerFactory.getLogger(SuscripcionController.class);

    private final GetAllSuscripcionesUseCase getAllSuscripcionesUseCase;
    private final UpdateSuscripcionPrecioUseCase updateSuscripcionPrecioUseCase;
    private final SuscripcionAssembler assembler;
    
    //Listar todas las suscripciones
    @GetMapping("/suscripciones")
    public SuscripcionWrapper getAllSuscripciones() {
        var suscripciones = getAllSuscripcionesUseCase.execute();
        logger.debug("Se encontraron {} suscripciones", suscripciones.size());
        return assembler.toWrapper(suscripciones);
    }
    
    //Actualizar el precio de una suscripción
    @PatchMapping("/suscripciones/{slug}/precio")
    public SuscripcionResponse updatePrecio(@PathVariable String slug, @Valid @RequestBody UpdateSuscripcionPrecioRequest request) {
        logger.debug("Nuevo precio: {}", request.getPrecio());
        var suscripcion = updateSuscripcionPrecioUseCase.execute(slug, request.getPrecio());
        return assembler.toResponse(suscripcion);
    }

}
