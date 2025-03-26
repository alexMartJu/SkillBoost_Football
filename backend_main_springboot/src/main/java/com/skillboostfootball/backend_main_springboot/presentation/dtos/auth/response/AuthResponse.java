package com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.response;

import com.skillboostfootball.backend_main_springboot.presentation.dtos.usuarios.response.UserResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private UserResponse user;
}
