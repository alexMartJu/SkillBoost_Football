package com.polideportivo.backend_springboot.domain.repository;

import com.polideportivo.backend_springboot.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByImageableTypeAndImageableId(String imageableType, Long imageableId);
}
