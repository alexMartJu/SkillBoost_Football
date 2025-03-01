package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.images;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import com.skillboostfootball.backend_main_springboot.domain.repositories.images.ImageRepository;
import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.images.ImageEntity;
import com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {
    
    private final SpringDataImageRepository repository;
    private final EntityMapper mapper;
    
    @Override
    public List<Image> findByEntityTypeAndEntityId(String entityType, Long entityId) {
        List<ImageEntity> entities = repository.findByImageableTypeAndImageableId(entityType, entityId);
        return entities.stream()
                .map(mapper::toImage)
                .collect(Collectors.toList());
    }
}
