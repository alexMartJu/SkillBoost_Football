package com.polideportivo.backend_springboot.api.security;

import com.polideportivo.backend_springboot.domain.exception.ExpiredTokenException;
import com.polideportivo.backend_springboot.domain.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final AuthProperties properties;
    private final UsuarioRepository repository;

    /** Generar access token */
    public String generateToken(String subject) {
        return buildToken(new HashMap<String, Object>(), subject, getAccessTokenExpiration(), getAccessTokenKey());
    }

    public String generateToken(Map<String, Object> extraClaims, String subject) {
        return buildToken(extraClaims, subject, getAccessTokenExpiration(), getAccessTokenKey());
    }

    /** Generar refresh token */
    public String generateRefreshToken(String subject) {
        return buildToken(new HashMap<String, Object>(), subject, getRefreshTokenExpiration(), getRefreshTokenKey());
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, String subject) {
        return buildToken(extraClaims, subject, getRefreshTokenExpiration(), getRefreshTokenKey());
    }

    /** Construir un token */
    private String buildToken(Map<String, Object> extraClaims, String subject, long expiration, Key key) {
        var nowMillis = System.currentTimeMillis();

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuer("polideportivo.backend_springboot")
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /** Validar access token */
    public boolean isTokenValid(String token, String subject) {
        var user = repository.findByEmail(subject);
        if (user.isEmpty()) {
            return false;
        }

        final String email = extractEmail(token, getAccessTokenKey());
        return email.equals(subject) && !isTokenExpired(token, getAccessTokenKey());
    }

    /** Validar refresh token */
    public boolean isRefreshTokenValid(String token, String subject) {
        var user = repository.findByEmail(subject);
        if (user.isEmpty()) {
            return false;
        }

        final String email = extractEmail(token, getRefreshTokenKey());
        return email.equals(subject) && !isTokenExpired(token, getRefreshTokenKey());
    }

    /** Validar expiración */
    public boolean isTokenExpired(String token, Key key) {
        System.out.println("token" + token);
        System.out.println("key" + key);
        return extractExpiration(token, key).before(new Date());
    }

    /** Extraer expiración */
    public Date extractExpiration(String token, Key key) {
        return extractClaim(token, Claims::getExpiration, key);
    }

    /** Extraer email */
    public String extractEmail(String token, Key key) {
        return extractClaim(token, Claims::getSubject, key);
    }

    /** Extraer claims */
    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction, Key key) {
        final Claims claims = extractAllClaims(token, key);
        return claimsTFunction.apply(claims);
    }

    /** Extraer todos los claims */
    private Claims extractAllClaims(String token, Key key) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new ExpiredTokenException("Token invalid");
        }
    }

    /** Obtener clave del access token */
    private Key getAccessTokenKey() {
        byte[] bytes = Decoders.BASE64.decode(properties.getToken().getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }

    /** Obtener clave del refresh token */
    private Key getRefreshTokenKey() {
        byte[] bytes = Decoders.BASE64.decode(properties.getRefreshToken().getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }

    /** Obtener expiración del access token */
    private long getAccessTokenExpiration() {
        return properties.getToken().getExpiration();
    }

    /** Obtener expiración del refresh token */
    private long getRefreshTokenExpiration() {
        return properties.getRefreshToken().getExpiration();
    }

    public Key getAccessTokenKeyPublic() {
        return getAccessTokenKey(); // Un getter público para acceder a la clave
    }

    public Key getRefreshTokenKeyPublic() {
        return getRefreshTokenKey(); // Un getter público para acceder a la clave
    }

    // private Key getKey() {
    // byte[] bytes = Decoders.BASE64.decode(properties.getToken().getSecret());
    // return Keys.hmacShaKeyFor(bytes);
    // }

    /**
     * Extraer el email de un token válido o expirado.
     * 
     * @param token el JWT.
     * @param key   la clave con la que se firmó el token.
     * @return el email contenido en el token.
     */
    public String extractEmailFromExpiredOrValidToken(String token, Key key) {
        Claims claims = extractClaimsFromToken(token, key); // Obtener los claims del token
        return claims.getSubject(); // Extraer el email (subject) de los claims
    }

    /**
     * Extraer los claims del token, incluso si ha expirado.
     * 
     * @param token el JWT.
     * @param key   la clave con la que se firmó el token.
     * @return los claims del token.
     */
    private Claims extractClaimsFromToken(String token, Key key) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) // Si el token está bien formado, extrae los claims
                    .getBody();
        } catch (ExpiredJwtException ex) {
            // Si el token ha expirado, podemos acceder a los claims a través de la
            // excepción
            return ex.getClaims(); // Devuelve los claims del token expirado
        } catch (Exception ex) {
            // Si hay un error diferente, lanzamos una excepción personalizada
            throw new RuntimeException("Error al extraer los claims del token", ex);
        }
    }

}
