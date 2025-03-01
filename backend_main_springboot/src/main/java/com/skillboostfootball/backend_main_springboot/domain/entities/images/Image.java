package com.skillboostfootball.backend_main_springboot.domain.entities.images;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    private Long id;
    private String imageUrl;
    private Long imageableId;
    private String imageableType;
}
