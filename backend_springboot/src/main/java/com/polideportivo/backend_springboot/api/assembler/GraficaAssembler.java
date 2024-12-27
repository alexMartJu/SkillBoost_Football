package com.polideportivo.backend_springboot.api.assembler;

import com.polideportivo.backend_springboot.api.model.grafica.GraficaWrapper;
import com.polideportivo.backend_springboot.api.model.grafica.GraficaResponse;
import com.polideportivo.backend_springboot.api.model.grafica.GraficaSeccionDTO;
import com.polideportivo.backend_springboot.domain.model.Grafica;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GraficaAssembler {
    
    // Convierte una entidad Grafica en su respuesta DTO
    public GraficaResponse toResponse(Grafica grafica) {
        // Creamos el DTO de la sección
        GraficaSeccionDTO graficaSeccionDTO = new GraficaSeccionDTO(grafica.getSeccion(), grafica.getNivel());

        // Creamos la respuesta de la gráfica
        GraficaResponse response = new GraficaResponse();
        response.setMes(grafica.getMes());
        response.setSecciones(List.of(graficaSeccionDTO));

        return response;
    }

    // Convierte una lista de entidades Grafica en un contenedor GraficaWrapper
    public GraficaWrapper toCollectionModel(List<Grafica> graficas) {
        // Agrupamos las gráficas por mes
        List<GraficaResponse> response = graficas.stream()
                .collect(Collectors.groupingBy(Grafica::getMes))
                .entrySet().stream()
                .map(entry -> {
                    GraficaResponse responseItem = new GraficaResponse();
                    responseItem.setMes(entry.getKey());
                    // Convertimos las gráficas del mes en secciones
                    responseItem.setSecciones(entry.getValue().stream()
                            .map(grafica -> new GraficaSeccionDTO(grafica.getSeccion(), grafica.getNivel()))
                            .collect(Collectors.toList()));
                    return responseItem;
                })
                .collect(Collectors.toList());

        // Devolvemos el contenedor con las respuestas
        return GraficaWrapper.builder().graficas(response).build();
    }
}
