package com.skillboostfootball.backend_main_springboot.application.security;

import com.skillboostfootball.backend_main_springboot.domain.exceptions.auth.TokenExpiredException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;
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
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final AuthProperties properties;
    private final UsuarioRepository usuarioRepository;

    @Override
    public String generateToken(Map<String, Object> extraClaims, String subject) {
        return buildToken(extraClaims, subject, properties.getToken().getExpiration(), getAccessTokenKey());
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, String subject) {
        return buildToken(extraClaims, subject, properties.getRefreshToken().getExpiration(), getRefreshTokenKey());
    }

    private String buildToken(Map<String, Object> extraClaims, String subject, long expiration, Key key) {
        var nowMillis = System.currentTimeMillis();

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuer("skillboostfootball.backend_main_springboot")
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String token, String subject) {
        var user = usuarioRepository.findByEmail(subject);
        if (user.isEmpty()) {
            return false;
        }

        final String email = extractEmail(token);
        return email.equals(subject) && !isTokenExpired(token, getAccessTokenKey());
    }

    @Override
    public boolean isRefreshTokenValid(String token, String subject) {
        var user = usuarioRepository.findByEmail(subject);
        if (user.isEmpty()) {
            return false;
        }

        try {
            final String email = extractEmailFromRefreshToken(token);
            return email.equals(subject) && !isTokenExpired(token, getRefreshTokenKey());
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token, Key key) {
        return extractExpiration(token, key).before(new Date());
    }

    private Date extractExpiration(String token, Key key) {
        return extractClaim(token, Claims::getExpiration, key);
    }

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject, getAccessTokenKey());
    }

    @Override
    public String extractEmailFromRefreshToken(String token) {
        return extractClaim(token, Claims::getSubject, getRefreshTokenKey());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTFunction, Key key) {
        final Claims claims = extractAllClaims(token, key);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token, Key key) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new TokenExpiredException();
        }
    }

    private Key getAccessTokenKey() {
        byte[] bytes = Decoders.BASE64.decode(properties.getToken().getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }

    private Key getRefreshTokenKey() {
        byte[] bytes = Decoders.BASE64.decode(properties.getRefreshToken().getSecret());
        return Keys.hmacShaKeyFor(bytes);
    }

    @Override
    public String extractEmailFromExpiredOrValid(String token) {
        try {
            //Primero intenta con access token
            return extractEmail(token);
        } catch (RuntimeException ex) {
            try {
                //Si falla, intenta con refresh token
                return extractEmailFromRefreshToken(token);
            } catch (RuntimeException refreshEx) {
                //Si ambos fallan con error normal, intenta extraer de un token expirado
                try {
                    Claims claims = Jwts
                            .parserBuilder()
                            .setSigningKey(getRefreshTokenKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
                    return claims.getSubject();
                } catch (ExpiredJwtException expiredEx) {
                    //Si expiró, obtener los claims del token expirado
                    return expiredEx.getClaims().getSubject();
                }
            }
        }
    }
}
