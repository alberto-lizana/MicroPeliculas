package com.microservicio.micropeliculas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.micropeliculas.dto.PeliculasResponseDTO;
import com.microservicio.micropeliculas.service.PeliculaService;

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
}
