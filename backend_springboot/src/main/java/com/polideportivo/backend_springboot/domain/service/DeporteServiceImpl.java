package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.model.Deporte;
import com.polideportivo.backend_springboot.domain.repository.DeporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeporteServiceImpl implements DeporteService {
    
    private final DeporteRepository repository;

    @Transactional(readOnly = true)
    public List<Deporte> getAllDeportes() {
        return repository.findAll();
    }
}
