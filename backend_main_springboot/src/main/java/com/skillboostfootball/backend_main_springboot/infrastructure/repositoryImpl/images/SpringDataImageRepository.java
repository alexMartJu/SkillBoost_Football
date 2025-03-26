package com.skillboostfootball.backend_main_springboot.infrastructure.repositoryImpl.images;

import com.skillboostfootball.backend_main_springboot.infrastructure.databases.entities.images.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByImageableTypeAndImageableId(String imageableType, Long imageableId);
}
