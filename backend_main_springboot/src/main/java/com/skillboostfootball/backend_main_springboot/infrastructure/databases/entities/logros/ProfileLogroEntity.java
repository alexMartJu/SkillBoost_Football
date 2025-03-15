package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_logros")
public class ProfileLogroEntity {
    @EmbeddedId
    private ProfileLogroId id;
    
    @Column(name = "fecha_logro")
    private LocalDateTime fechaLogro;
    
    @Column(name = "progreso_actual")
    private Integer progresoActual;
    
    @Column(name = "notificado")
    private Boolean notificado;
    
    //Métodos de conveniencia para acceder a los componentes del ID
    @Transient
    public Long getProfileId() {
        return id != null ? id.getProfileId() : null;
    }
    
    @Transient
    public Long getLogroId() {
        return id != null ? id.getLogroId() : null;
    }
    
    //Métodos de conveniencia para establecer los componentes del ID
    public void setProfileId(Long profileId) {
        if (id == null) {
            id = new ProfileLogroId();
        }
        id.setProfileId(profileId);
    }
    
    public void setLogroId(Long logroId) {
        if (id == null) {
            id = new ProfileLogroId();
        }
        id.setLogroId(logroId);
    }
}
