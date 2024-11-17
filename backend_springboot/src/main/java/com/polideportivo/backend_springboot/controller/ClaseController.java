package com.polideportivo.backend_springboot.controller;

import com.polideportivo.backend_springboot.model.Clase;
import com.polideportivo.backend_springboot.repository.ClaseRepository;
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
public class ClaseController {
    @Autowired
    private ClaseRepository claseRepository;

    // Obtener todas las clases
    @GetMapping("/clases")
    public ResponseEntity<List<Clase>> getAllClases() {
        try {
            List<Clase> clases = new ArrayList<>();
            claseRepository.findAll().forEach(clases::add);
            return new ResponseEntity<>(clases, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener una clase por ID
    @GetMapping("/clases/{id}")
    public ResponseEntity<Clase> getClaseById(@PathVariable Long id) {
        try {
            Optional<Clase> clase = claseRepository.findById(id);
            return clase.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
