package com.microservicio.micropeliculas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.micropeliculas.dto.CrearPeliculaDTO;
import com.microservicio.micropeliculas.dto.PeliculasResponseDTO;
import com.microservicio.micropeliculas.service.PeliculaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/peliculas")

public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PeliculasResponseDTO>> getAllPeliculas(){
        return ResponseEntity.ok(peliculaService.getAllPeliculas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculasResponseDTO> getPeliculaById(@PathVariable Long id){
        return ResponseEntity.ok(peliculaService.getPeliculaById(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<PeliculasResponseDTO> crearPelicula(@Valid @RequestBody CrearPeliculaDTO crearPeliculaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.crearPelicula(crearPeliculaDTO));
    }
}
