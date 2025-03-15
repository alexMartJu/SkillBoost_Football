package com.skillboostfootball.backend_main_springboot.presentation.assemblers.graficas;

import com.skillboostfootball.backend_main_springboot.domain.entities.graficas.Grafica;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.response.GraficaResponse;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.response.GraficaSectionDTO;
import com.skillboostfootball.backend_main_springboot.presentation.dtos.graficas.response.GraficaWrapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GraficaAssembler {

    public GraficaWrapper toWrapper(List<Grafica> graficas) {
        
        //Agrupamos las gráficas por mes
        Map<Integer, List<Grafica>> graficasByMonth = graficas.stream().collect(Collectors.groupingBy(Grafica::getMes));

        //Se convierte cada grupo en un GraficaResponse
        List<GraficaResponse> responseList = graficasByMonth.entrySet().stream()
                .map(entry -> {
                    GraficaResponse response = new GraficaResponse();
                    response.setMes(entry.getKey());

                    //Convertimos cada gráfica en una sección
                    List<GraficaSectionDTO> secciones = entry.getValue().stream()
                            .map(grafica -> new GraficaSectionDTO(grafica.getSeccion(), grafica.getNivel()))
                            .collect(Collectors.toList());

                    response.setSecciones(secciones);
                    return response;
                })
                .collect(Collectors.toList());

        return GraficaWrapper.builder()
                .graficas(responseList)
                .build();

    }
}
