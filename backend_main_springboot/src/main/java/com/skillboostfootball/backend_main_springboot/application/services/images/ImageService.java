package com.skillboostfootball.backend_main_springboot.application.services.images;

import com.skillboostfootball.backend_main_springboot.domain.entities.images.Image;
import java.util.List;

public interface ImageService {
    
    List<Image> getImagesForEntity(final String type, final Long id);
    
}
