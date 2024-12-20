package com.polideportivo.backend_springboot.api.security;

import java.util.Map;
import java.util.Date;
import java.security.Key;

public interface TokenService {
    
    String generateToken(final String subject);

    String generateToken(final Map<String, Object> extraClaims, final String subject);

    String generateRefreshToken(final String subject);

    String generateRefreshToken(final Map<String, Object> extraClaims, final String subject);

    boolean isTokenValid(final String token, final String subject);

    boolean isRefreshTokenValid(final String token, final String subject);

    boolean isTokenExpired(final String token, final Key key);

    Date extractExpiration(String token, final Key key);

    String extractEmail(final String token, final Key key);

    Key getAccessTokenKeyPublic();

    Key getRefreshTokenKeyPublic();

    String extractEmailFromExpiredOrValidToken(final String token, final Key key);
}
