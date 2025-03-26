package com.skillboostfootball.backend_main_springboot.application.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("api.security")
public class AuthProperties {
    
    @NotNull
    private Token token;

    @NotNull
    private RefreshToken refreshToken;

    @Getter
    @Setter
    @Validated
    public static class Token {
        @NotBlank
        private String secret;

        @NotNull
        private long expiration;
    }

    @Getter
    @Setter
    @Validated
    public static class RefreshToken {
        @NotBlank
        private String secret;

        @NotNull
        private long expiration;
    }
}
