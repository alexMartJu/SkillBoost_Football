package com.skillboostfootball.backend_main_springboot.domain.repositories.blacklistToken;

import java.util.Optional;

import com.skillboostfootball.backend_main_springboot.domain.entities.blacklistToken.BlacklistToken;

public interface BlacklistTokenRepository {

    Optional<BlacklistToken> findByRefreshToken(String refreshToken);
    BlacklistToken save(BlacklistToken blacklistToken);
    
}
