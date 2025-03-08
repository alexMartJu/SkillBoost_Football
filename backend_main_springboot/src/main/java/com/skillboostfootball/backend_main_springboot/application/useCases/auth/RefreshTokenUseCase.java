package com.skillboostfootball.backend_main_springboot.application.useCases.auth;

import com.skillboostfootball.backend_main_springboot.application.security.TokenService;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.InvalidCredentialsException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.UsuarioNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.blacklistToken.BlacklistTokenRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCase {
    private final UsuarioRepository usuarioRepository;
    private final BlacklistTokenRepository blacklistTokenRepository;
    private final TokenService tokenService;
    
    @Transactional
    public RefreshResult execute(String refreshToken) {
        //Verificar si el token está en la blacklist
        if (blacklistTokenRepository.findByRefreshToken(refreshToken).isPresent()) {
            throw new InvalidCredentialsException();
        }
        
        //Extraer el email del token
        String email = tokenService.extractEmailFromRefreshToken(refreshToken);
        //Buscar usuario por email
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
        
        //Verificar que el refresh token coincida con el almacenado
        if (!refreshToken.equals(usuario.getRefreshToken())) {
            throw new InvalidCredentialsException();
        }
        
        //Verificar validez del token
        if (!tokenService.isRefreshTokenValid(refreshToken, email)) {
            throw new InvalidCredentialsException();
        }
        
        //Preparar claims personalizados
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", usuario.getId());
        claims.put("userType", usuario.getTipoUsuario());
        
        //Generar nuevo access token
        String newAccessToken = tokenService.generateToken(claims, email);
        
        //Devolver resultado
        return new RefreshResult(newAccessToken);

    }
    
    public record RefreshResult(String accessToken) {}

}
