package com.skillboostfootball.backend_main_springboot.domain.entities.images;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String imageUrl;
    private Long imageableId;
    private String imageableType;
}
