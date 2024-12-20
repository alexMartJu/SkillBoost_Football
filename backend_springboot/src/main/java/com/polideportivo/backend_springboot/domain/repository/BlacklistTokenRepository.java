package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.BlacklistToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistTokenRepository extends JpaRepository<BlacklistToken, Long> {
    
    // Verifica si un token ya está en la blacklist
    Optional<BlacklistToken> findByRefreshToken(String refreshToken);
}
