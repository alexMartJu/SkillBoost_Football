package com.skillboostfootball.backend_main_springboot.presentation.dtos.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenRefreshResponse {
    private String accessToken;
}
