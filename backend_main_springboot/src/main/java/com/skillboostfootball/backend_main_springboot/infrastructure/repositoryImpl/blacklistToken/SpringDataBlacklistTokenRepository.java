package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.blacklistToken;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.blacklistToken.BlacklistTokenEntity;
import java.util.Optional;

public interface SpringDataBlacklistTokenRepository extends JpaRepository<BlacklistTokenEntity, Long> {

    Optional<BlacklistTokenEntity> findByRefreshToken(String refreshToken);
    
}
