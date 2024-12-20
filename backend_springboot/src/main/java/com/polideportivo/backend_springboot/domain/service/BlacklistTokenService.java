package com.polideportivo.backend_springboot.domain.service;

public interface BlacklistTokenService {
    
    void addTokenToBlacklist(final String refreshToken, final Long userId);

    boolean isTokenBlacklisted(final String refreshToken);

}
