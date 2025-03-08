package com.skillboostfootball.backend_main_springboot.domain.entities.blacklistToken;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BlacklistToken {
    private Long id;
    private Long usuarioId;
    private String refreshToken;
    private LocalDateTime revokeTime;
}