package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Image;

import java.util.List;

public interface ImageService {
    
    List<Image> getImagesForEntity(final String type, final Long id);
    
}
