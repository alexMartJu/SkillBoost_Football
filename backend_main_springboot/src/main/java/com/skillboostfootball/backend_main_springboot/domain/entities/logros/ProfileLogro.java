package com.skillboostfootball.backend_main_springboot.domain.entities.logros;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProfileLogro {
    private Long profileId;
    private Long logroId;
    private LocalDateTime fechaLogro;
    private Integer progresoActual;
    private Boolean notificado;
    
    public void actualizarProgreso(Integer nuevoProgreso) {
        this.progresoActual = nuevoProgreso;
    }
    
    public void marcarComoNotificado() {
        this.notificado = true;
    }
}
