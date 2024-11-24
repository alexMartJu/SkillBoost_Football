package com.polideportivo.backend_springboot.domain.service;

import com.polideportivo.backend_springboot.domain.exception.DeporteNotFoundException;
import com.polideportivo.backend_springboot.domain.model.Pista;
import com.polideportivo.backend_springboot.domain.repository.PistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PistaServiceImpl implements PistaService {
    private final PistaRepository repository;

    @Transactional(readOnly = true)
    public List<Pista> getAllPistas() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Pista getBySlug(String slug) {
        return repository.findBySlug(slug).orElseThrow(DeporteNotFoundException::new);
    }
}
