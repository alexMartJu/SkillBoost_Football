package com.polideportivo.backend_springboot.controller;

import com.polideportivo.backend_springboot.model.Deporte;
import com.polideportivo.backend_springboot.model.Pista;
import com.polideportivo.backend_springboot.repository.PistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class PistaController {

    @Autowired
    private PistaRepository pistaRepository;

    // Obtener todas las pistas
    @GetMapping("/pistas")
    public ResponseEntity<List<Pista>> getAllPistas() {
        try {
            List<Pista> pistas = new ArrayList<>();
            pistaRepository.findAll().forEach(pistas::add);
            return new ResponseEntity<>(pistas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una pista por ID
    @GetMapping("/pistas/{id}")
    public ResponseEntity<Pista> getPistaById(@PathVariable Long id) {
        try {
            Optional<Pista> pista = pistaRepository.findById(id);
            return pista.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener los deportes asociados a una pista
    @GetMapping("/pistas/{id}/deportes")
    public ResponseEntity<List<Deporte>> getDeportesByPista(@PathVariable Long id) {
        try {
            Optional<Pista> pista = pistaRepository.findById(id);
            if (pista.isPresent()) {
                List<Deporte> deportes = new ArrayList<>(pista.get().getDeportes());
                return new ResponseEntity<>(deportes, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
