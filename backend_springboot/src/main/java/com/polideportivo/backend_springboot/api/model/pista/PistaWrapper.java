package com.polideportivo.backend_springboot.api.model.pista;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PistaWrapper {
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
    private List<PistaResponse> pistas;
}
