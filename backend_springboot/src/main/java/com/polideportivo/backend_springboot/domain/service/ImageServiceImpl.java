package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Image;
import com.polideportivo.backend_springboot.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    
    @Autowired
    private ImageRepository repository;

    @Transactional(readOnly = true)
    public List<Image> getImagesForEntity(String type, Long id) {
        return repository.findByImageableTypeAndImageableId(type, id);
    }
}
