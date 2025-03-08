package com.skillboostfootball.backend_main_springboot.application.useCases.auth;

import com.skillboostfootball.backend_main_springboot.application.security.TokenService;
import com.skillboostfootball.backend_main_springboot.domain.entities.blacklistToken.BlacklistToken;
import com.skillboostfootball.backend_main_springboot.domain.entities.usuarios.Usuario;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.InvalidCredentialsException;
import com.skillboostfootball.backend_main_springboot.domain.exceptions.usuarios.UsuarioNotFoundException;
import com.skillboostfootball.backend_main_springboot.domain.repositories.blacklistToken.BlacklistTokenRepository;
import com.skillboostfootball.backend_main_springboot.domain.repositories.usuarios.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LogoutUserUseCase {
    private final UsuarioRepository usuarioRepository;
    private final BlacklistTokenRepository blacklistTokenRepository;
    private final TokenService tokenService;
    
    @Transactional
    public void execute(String refreshToken) {

        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        
        //Verificar si el token ya está en la blacklist
        if (blacklistTokenRepository.findByRefreshToken(refreshToken).isPresent()) {
            throw new InvalidCredentialsException();
        }
        
        //Extraer el email del token (aunque haya expirado)
        String email = tokenService.extractEmailFromExpiredOrValid(refreshToken);
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(UsuarioNotFoundException::new);
        
        //Agregar el token a la blacklist
        BlacklistToken blacklistToken = BlacklistToken.builder()
            .usuarioId(usuario.getId())
            .refreshToken(refreshToken)
            .revokeTime(LocalDateTime.now())
            .build();
        
        blacklistTokenRepository.save(blacklistToken);
        // Eliminar el refresh token del usuario
        usuario.setRefreshToken(null);
        usuarioRepository.save(usuario);

    }
}
