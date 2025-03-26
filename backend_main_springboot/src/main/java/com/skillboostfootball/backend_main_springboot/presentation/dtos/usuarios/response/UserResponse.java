package com.skillboostfootball.backend_main_springboot.presentation.dtos.usuarios.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.skillboostfootball.backend_main_springboot.presentation.dtos.profiles.response.ProfileResponse;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String tipoUsuario;
    private String status;
    private ProfileResponse profile;
    private List<String> roles;
}
