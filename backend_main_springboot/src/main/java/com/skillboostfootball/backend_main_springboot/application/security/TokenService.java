package com.skillboostfootball.backend_main_springboot.application.security;

import java.util.Map;

public interface TokenService {

    String generateToken(Map<String, Object> claims, String subject);
    String generateRefreshToken(Map<String, Object> claims, String subject);
    boolean isTokenValid(String token, String subject);
    boolean isRefreshTokenValid(String token, String subject);
    String extractEmail(String token);
    String extractEmailFromRefreshToken(String token);
    String extractEmailFromExpiredOrValid(String token);
    
}
