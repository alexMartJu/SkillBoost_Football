package com.skillboostfootball.backend_main_springboot.domain.entities.profileSuscripciones;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.skillboostfootball.backend_main_springboot.domain.entities.suscripciones.Suscripcion;

@Getter
@Builder
public class ProfileSuscripcion {
    private Long id;
    private Long profileId;
    private Long suscripcionId;
    private Suscripcion suscripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String status;
    private String metodoPago;
    private Long ultimoPagoId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    //Métodos de dominio
    public void activar(Long pagoId) {
        this.status = "active";
        this.ultimoPagoId = pagoId;
        this.updatedAt = LocalDateTime.now();
    }
    
    public void cancelar() {
        this.status = "canceled";
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isActiva() {
        return "active".equals(this.status) && 
               (fechaFin == null || fechaFin.isAfter(LocalDate.now()) || fechaFin.isEqual(LocalDate.now()));
    }
    
    public void extenderFechas(LocalDate nuevaFechaFin) {
        this.fechaFin = nuevaFechaFin;
        this.updatedAt = LocalDateTime.now();
    }
}
