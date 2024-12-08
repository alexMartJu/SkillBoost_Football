package com.polideportivo.backend_springboot.api.security;

import java.util.Map;
import java.util.Date;

public interface TokenService {
    
    String generateToken(final String subject);

    String generateToken(final Map<String, Object> extraClaims, final String subject);

    boolean isTokenValid(final String token, final String subject);

    boolean isTokenExpired(final String token);

    Date extractExpiration(String token);

    String extractEmail(final String token);
}
