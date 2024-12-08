package com.polideportivo.backend_springboot.api.security;

import com.polideportivo.backend_springboot.api.assembler.UsuarioAssembler;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioAuthenticate;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioResponse;
import com.polideportivo.backend_springboot.api.model.usuario.UsuarioToken;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import com.polideportivo.backend_springboot.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    
    private final UsuarioService userService;
    private final TokenService tokenService;
    private final UsuarioAssembler userAssembler;
    private final AuthenticationManager authenticationManager;

    public UsuarioResponse register(Usuario user) {
        return userAssembler.toResponse(user);
    }

    public UsuarioResponse authenticate(UsuarioAuthenticate authenticate) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticate.getEmail(), authenticate.getPassword())
        );

        var user = userService.getByEmail(authenticate.getEmail());
        var token = tokenService.generateToken(setDefaultClaims(user), user.getEmail());

        return toUserResponse(user, token);
    }

    private UsuarioResponse toUserResponse(Usuario user, String token) {
        var response = userAssembler.toResponse(user);
        response.setToken(token); // Agregar el token a la respuesta
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

}