package com.polideportivo.backend_springboot.api.model.pistaPrivada;

import com.polideportivo.backend_springboot.domain.model.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PistaPrivadaResponse {
    
    private String nombre;
    private String slug;
    private String info;

    private List<Image> images;
}
