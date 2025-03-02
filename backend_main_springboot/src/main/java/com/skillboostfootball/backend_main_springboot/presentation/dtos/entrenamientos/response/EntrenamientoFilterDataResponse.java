package com.skillboostfootball.backend_main_springboot.presentation.dtos.entrenamientos.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EntrenamientoFilterDataResponse {
    private Long totalEntrenamientos;
    private Integer plazasMinimas;
    private Integer plazasMaximas;
}
