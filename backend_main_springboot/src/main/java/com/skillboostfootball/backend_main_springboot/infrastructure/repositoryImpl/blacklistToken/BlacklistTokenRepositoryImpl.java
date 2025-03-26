package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.blacklistToken;

import com.skillboostfootball.backend_main_springboot.domain.entities.blacklistToken.BlacklistToken;
import com.skillboostfootball.backend_main_springboot.domain.repositories.blacklistToken.BlacklistTokenRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.blacklistToken.BlacklistTokenEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BlacklistTokenRepositoryImpl implements BlacklistTokenRepository {
    private final SpringDataBlacklistTokenRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public Optional<BlacklistToken> findByRefreshToken(String refreshToken) {
        return repository.findByRefreshToken(refreshToken).map(mapper::toBlacklistToken);
    }
    
    @Override
    public BlacklistToken save(BlacklistToken blacklistToken) {
        BlacklistTokenEntity entity = mapper.toBlacklistTokenEntity(blacklistToken);
        entity = repository.save(entity);
        return mapper.toBlacklistToken(entity);
    }
}
