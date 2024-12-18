package com.polideportivo.backend_springboot.api.model.profile;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.polideportivo.backend_springboot.api.model.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeName("profile")
public class ProfileResponse  extends BaseResponse {
    
    private String numeroSocio;
    private String nombre;
    private String bio;
    private String image;
    private String apellidos;
    private Integer edad;
}
