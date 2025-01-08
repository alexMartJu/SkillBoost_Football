package com.polideportivo.backend_springboot.api.model.entrenador;

import com.polideportivo.backend_springboot.domain.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntrenadorInfoResponse {
    
    private String nombre;
    private String apellidos;
    private List<Image> images;

}
