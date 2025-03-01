package com.skillboostfootball.backend_main_springboot.domain.repositories.images;

import java.util.List;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;

public interface ImageRepository {
    
    List<Image> findByEntityTypeAndEntityId(String entityType, Long entityId);
}
