package com.microservicio.micropeliculas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.micropeliculas.dto.DirectoresResponseDTO;
import com.microservicio.micropeliculas.service.DirectorService;

@RestController
@RequestMapping("/directores")

public class DirectorController {

    private final DirectorService directorService;

    public DirectorController(DirectorService directorService) {
        this.directorService = directorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DirectoresResponseDTO>> getAllDirectores(){
        return ResponseEntity.ok(directorService.getAllDirectores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectoresResponseDTO> getDirectorById(@PathVariable Long id){
        return ResponseEntity.ok(directorService.getDirectorById(id));
    }    
}
