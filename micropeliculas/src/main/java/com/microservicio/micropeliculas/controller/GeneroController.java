package com.microservicio.micropeliculas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.micropeliculas.dto.GenerosResponseDTO;
import com.microservicio.micropeliculas.service.GeneroService;

@RestController
@RequestMapping("/generos")

public class GeneroController {

    private final GeneroService generoService;


    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<GenerosResponseDTO>> getAllGeneros() {
        
        return ResponseEntity.ok(generoService.getAllGeneros());
    }


    @GetMapping("/{id}")
    public ResponseEntity<GenerosResponseDTO> getGeneroById(@PathVariable Long id) {
        return ResponseEntity.ok(generoService.getGeneroById(id));
    }
}
