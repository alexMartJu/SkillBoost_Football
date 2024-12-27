package com.polideportivo.backend_springboot.api.controller;

import com.polideportivo.backend_springboot.api.assembler.GraficaAssembler;
import com.polideportivo.backend_springboot.api.model.grafica.GraficaWrapper;
import com.polideportivo.backend_springboot.api.security.authorization.CheckSecurity;
import com.polideportivo.backend_springboot.domain.service.GraficaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GraficaController {
    
    private final GraficaService graficaService;
    private final GraficaAssembler graficaAssembler;

    // Endpoint para obtener las gráficas del usuario actual por año
    @GetMapping("/profileGraficas")
    @CheckSecurity.Protected.canManage
    public GraficaWrapper getGraficas(@RequestParam Integer año) {

        // Obtenemos las gráficas con el servicio
        var graficas = graficaService.getGraficasByAuthenticatedUserAndAnio(año);

        // Convertimos las gráficas a la respuesta DTO
        return graficaAssembler.toCollectionModel(graficas);
    }
}
