package com.skillboostfootball.backend_main_springboot.domain.repositories.logros;

import com.skillboostfootball.backend_main_springboot.domain.entities.logros.ProfileLogro;
import java.util.List;
import java.util.Optional;

public interface ProfileLogroRepository {

    List<ProfileLogro> findByProfileId(Long profileId);
    Optional<ProfileLogro> findByProfileIdAndLogroId(Long profileId, Long logroId);
    ProfileLogro save(ProfileLogro profileLogro);
    List<ProfileLogro> findByProfileIdAndNotNotificado(Long profileId);
    
}
