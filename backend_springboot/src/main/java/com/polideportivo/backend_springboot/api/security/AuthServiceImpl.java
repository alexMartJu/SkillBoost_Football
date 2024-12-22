package com.polideportivo.backend_springboot.api.security;

import com.polideportivo.backend_springboot.api.assembler.UsuarioAssembler;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioAuthenticate;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioNewAccessTokenResponse;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioToken;
import com.polideportivo.backend_springboot.domain.exception.ExpiredTokenException;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import com.polideportivo.backend_springboot.domain.service.BlacklistTokenService;
import com.polideportivo.backend_springboot.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import com.polideportivo.backend_springboot.domain.repository.EntrenadorRepository;
import com.polideportivo.backend_springboot.domain.repository.AdminRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioService userService;
    private final TokenService tokenService;
    private final UsuarioAssembler userAssembler;
    private final AuthenticationManager authenticationManager;
    private final EntrenadorRepository entrenadorRepository;
    private final AdminRepository adminRepository;
    private final WebClient.Builder webClientBuilder;
    private final BlacklistTokenService blacklistTokenService;

    public UsuarioResponse register(Usuario user) {
        return userAssembler.toResponse(user);
    }

    public Object authenticate(UsuarioAuthenticate authenticate) {
        // Comprobamos si el email pertenece a un entrenador o admin
        if (entrenadorRepository.existsByEmail(authenticate.getEmail())) {
            // Redirigir al endpoint de Laravel para entrenadores
            return authenticateViaLaravel(authenticate, "entrenador");
        } else if (adminRepository.existsByEmail(authenticate.getEmail())) {
            // Redirigir al endpoint de Laravel para admins
            return authenticateViaLaravel(authenticate, "admin");
        }

        // Si el email pertenece a un usuario, hacer autenticación tradicional en Spring
        // Boot
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticate.getEmail(), authenticate.getPassword()));

        var user = userService.getByEmail(authenticate.getEmail());
        // Generar los tokens (Access y Refresh)
        var token = tokenService.generateToken(setDefaultClaims(user), user.getEmail());
        var refreshToken = tokenService.generateRefreshToken(setDefaultClaims(user), user.getEmail());

        userService.setRefreshToken(user, refreshToken);

        // Crear la respuesta para un usuario (como estaba antes)
        return toUserResponse(user, token, refreshToken); // Devolvemos la respuesta en formato UsuarioResponse
    }

    private UsuarioResponse toUserResponse(Usuario user, String token, String refreshToken) {
        var response = userAssembler.toResponse(user);
        response.setToken(token); // Agregar el token a la respuesta
        response.setRefreshToken(refreshToken);
        return response;
    }

    private Map<String, Object> setDefaultClaims(Usuario user) {
        var claims = new HashMap<String, Object>();
        var userToken = UsuarioToken
                .builder()
                .id(user.getId())
                .build();

        claims.put("user", userToken);
        return claims;
    }

    private Object authenticateViaLaravel(UsuarioAuthenticate authenticate, String tipo) {
        String endpoint;
        if ("entrenador".equals(tipo)) {
            System.out.println("Entro Entrenador");
            endpoint = "http://laravel:3000/api/entrenador/login";
            System.out.println("Endpoint: " + endpoint);
        } else if ("admin".equals(tipo)) {
            System.out.println("Entro Admin");
            endpoint = "http://laravel:3000/api/admin/login";
            System.out.println("Endpoint: " + endpoint);
        } else {
            throw new IllegalArgumentException("Tipo no válido para autenticación en Laravel");
        }

        try {
            // Realizamos la solicitud a Laravel y devolvemos la respuesta tal cual
            return webClientBuilder.build()
                    .post()
                    .uri(endpoint)
                    .bodyValue(authenticate)
                    .retrieve()
                    .toEntity(Object.class) // Aquí usamos Object.class
                    .block()
                    .getBody(); // Devolvemos el cuerpo de la respuesta
        } catch (Exception e) {
            throw new RuntimeException("Error al autenticar con Laravel: " + e.getMessage());
        }
    }

    // Refrescar el Access Token
    public UsuarioNewAccessTokenResponse refreshAccessToken(String refreshToken) {
        // Validar si el Refresh Token está en la blacklist
        if (blacklistTokenService.isTokenBlacklisted(refreshToken)) {
            throw new RuntimeException("Refresh token is blacklisted");
        }

        // Extraer el email (subject) del Refresh Token
        String email = tokenService.extractEmail(refreshToken, tokenService.getRefreshTokenKeyPublic());

        // Verificar que el usuario asociado existe
        var user = userService.getByEmail(email);

        // Generar un nuevo Access Token
        String token = tokenService.generateToken(setDefaultClaims(user), email);

        return userAssembler.toNewAccessTokenResponse(token);
    }

    // Logout del usuario
    public void logout(String refreshToken) {
        // Verificar que el Refresh Token haya sido proporcionado
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new RuntimeException("Refresh token is required");
        }

        // Validar si el Refresh Token está en la blacklist
        if (blacklistTokenService.isTokenBlacklisted(refreshToken)) {
            throw new RuntimeException("Refresh token is already blacklisted");
        }

        // Extraer el email del token, independientemente de si ha expirado o no
        String email = tokenService.extractEmailFromExpiredOrValidToken(refreshToken,
                tokenService.getRefreshTokenKeyPublic());

        // Verificar que el usuario asociado existe
        var user = userService.getByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Añadir el Refresh Token a la Blacklist
        blacklistTokenService.addTokenToBlacklist(refreshToken, user.getId());

        userService.setRefreshToken(user, null);
    }

}