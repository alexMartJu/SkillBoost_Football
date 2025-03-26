package com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.logros;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ProfileLogroId implements Serializable {
    @Column(name = "profile_id")
    private Long profileId;
    
    @Column(name = "logro_id")
    private Long logroId;
}
