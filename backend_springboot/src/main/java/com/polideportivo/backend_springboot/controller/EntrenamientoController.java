package com.polideportivo.backend_springboot.controller;

import com.polideportivo.backend_springboot.model.Entrenamiento;
import com.polideportivo.backend_springboot.repository.EntrenamientoRepository;
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
public class EntrenamientoController {

    @Autowired
    private EntrenamientoRepository entrenamientoRepository;

    // Obtener todos los entrenamientos
    @GetMapping("/entrenamientos")
    public ResponseEntity<List<Entrenamiento>> getAllEntrenamientos() {
        try {
            List<Entrenamiento> entrenamientos = new ArrayList<>();
            entrenamientoRepository.findAll().forEach(entrenamientos::add);
            return new ResponseEntity<>(entrenamientos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener un entrenamiento por ID
    @GetMapping("/entrenamientos/{id}")
    public ResponseEntity<Entrenamiento> getEntrenamientoById(@PathVariable Long id) {
        try {
            Optional<Entrenamiento> entrenamiento = entrenamientoRepository.findById(id);
            return entrenamiento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                               .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
