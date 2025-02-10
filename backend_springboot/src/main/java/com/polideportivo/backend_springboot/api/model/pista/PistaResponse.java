package com.polideportivo.backend_springboot.api.model.pista;

import java.util.List;

import com.polideportivo.backend_springboot.domain.model.Image;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PistaResponse {
    private Long id;
    private String nombre;
    private String slug;
    private String info;
    private List<Image> images;
}
