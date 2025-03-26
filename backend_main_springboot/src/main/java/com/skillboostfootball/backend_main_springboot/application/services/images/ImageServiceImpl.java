package com.skillboostfootball.backend_main_springboot.application.services.images;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import com.skillboostfootball.backend_main_springboot.domain.repositories.images.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    
    private final ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Image> getImagesForEntity(String entityType, Long entityId) {
        return imageRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }
}
