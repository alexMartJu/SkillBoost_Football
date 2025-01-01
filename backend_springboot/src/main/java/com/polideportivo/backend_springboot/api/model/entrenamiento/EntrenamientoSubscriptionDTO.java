package com.polideportivo.backend_springboot.api.model.entrenamiento;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntrenamientoSubscriptionDTO {
    
    private String slug;

    @JsonProperty("isSubscribed")
    private boolean isSubscribed;
    
}
