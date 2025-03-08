package com.skillboostfootball.backend_main_springboot.application.useCases.auth;


import com.skillboostfootball.backend_main_springboot.application.security.TokenService;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.InvalidCredentialsException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.UsuarioNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticateUserUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    
    @Transactional
    public AuthResult execute(String email, String password) {

        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
        
        //Verificar contraseña
        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new InvalidCredentialsException();
        }
        
        //Verificar que el usuario esté activo
        if (!usuario.isActive()) {
            throw new InvalidCredentialsException();
        }
        
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", usuario.getId());
        claims.put("userType", usuario.getTipoUsuario());
        
        //Generar tokens (access y refresh)
        String accessToken = tokenService.generateToken(claims, email);
        String refreshToken = tokenService.generateRefreshToken(claims, email);
        
        //Actualizar el refresh token en la base de datos
        usuario.setRefreshToken(refreshToken);
        usuarioRepository.save(usuario);
        return new AuthResult(accessToken, refreshToken);

    }
    
    public record AuthResult(String accessToken, String refreshToken) {}
    
}
