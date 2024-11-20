package com.polideportivo.backend_springboot.controller;

import com.polideportivo.backend_springboot.model.Deporte;
import com.polideportivo.backend_springboot.model.Pista;
import com.polideportivo.backend_springboot.model.Entrenamiento;
import com.polideportivo.backend_springboot.repository.DeporteRepository;
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
public class DeporteController {

    @Autowired
    private DeporteRepository deporteRepository;

    // Obtener todos los deportes
    @GetMapping("/deportes")
    public ResponseEntity<List<Deporte>> getAllDeportes() {
        try {
            List<Deporte> deportes = new ArrayList<>();
            deporteRepository.findAll().forEach(deportes::add);
            return new ResponseEntity<>(deportes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener un deporte por ID
    @GetMapping("/deportes/{id}")
    public ResponseEntity<Deporte> getDeporteById(@PathVariable Long id) {
        try {
            Optional<Deporte> deporte = deporteRepository.findById(id);
            return deporte.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                          .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener las pistas de un deporte
    @GetMapping("/deportes/{id}/pistas")
    public ResponseEntity<List<Pista>> getPistasByDeporte(@PathVariable Long id) {
        try {
            Optional<Deporte> deporte = deporteRepository.findById(id);
            if (deporte.isPresent()) {
                List<Pista> pistas = new ArrayList<>(deporte.get().getPistas());
                return new ResponseEntity<>(pistas, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener los entrenamientos de un deporte
    @GetMapping("/deportes/{id}/entrenamientos")
    public ResponseEntity<List<Entrenamiento>> getEntrenamientosByDeporte(@PathVariable Long id) {
        try {
            Optional<Deporte> deporte = deporteRepository.findById(id);
            if (deporte.isPresent()) {
                List<Entrenamiento> entrenamientos = new ArrayList<>(deporte.get().getEntrenamientos());
                return new ResponseEntity<>(entrenamientos, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
