package com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshTokenRequest {
    @NotBlank(message = "Refresh token es obligatorio")
    private String refreshToken;
}
