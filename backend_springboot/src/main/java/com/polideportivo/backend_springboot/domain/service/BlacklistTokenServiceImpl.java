package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.BlacklistToken;
import com.polideportivo.backend_springboot.domain.model.Usuario;
import com.polideportivo.backend_springboot.domain.repository.BlacklistTokenRepository;
import com.polideportivo.backend_springboot.domain.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlacklistTokenServiceImpl implements BlacklistTokenService {
    
    private final BlacklistTokenRepository blacklistTokenRepository;
    private final UsuarioRepository userRepository;

    // Agregar un token a la blacklist
    public void addTokenToBlacklist(String refreshToken, Long userId) {

        // Obtener el usuario por su ID
        Usuario user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        var blacklistToken = BlacklistToken.builder()
                .refreshToken(refreshToken)
                .user(user)
                .revokeTime(LocalDateTime.now())
                .build();

        blacklistTokenRepository.save(blacklistToken);
    }

    // Verificar si un token está en la blacklist
    public boolean isTokenBlacklisted(String refreshToken) {
        return blacklistTokenRepository.findByRefreshToken(refreshToken).isPresent();
    }
}
