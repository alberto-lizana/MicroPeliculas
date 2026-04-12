package com.microservicio.micropeliculas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.micropeliculas.dto.PeliculaRequestDTO;
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

    @GetMapping("/all-disponibles")
    public ResponseEntity<List<PeliculasResponseDTO>> getAllPeliculasDisponibles(){
        List<PeliculasResponseDTO> peliculasDisponibles = peliculaService.getAllPeliculasDisponibles();
        return ResponseEntity.ok(peliculasDisponibles);
    }

    @GetMapping("/all-no-disponibles")
    public ResponseEntity<List<PeliculasResponseDTO>> getAllPeliculasNoDisponibles(){
        List<PeliculasResponseDTO> peliculasNoDisponibles = peliculaService.getAllPeliculasNoDisponibles();
        return ResponseEntity.ok(peliculasNoDisponibles);
    }    

    @PostMapping("/crear")
    public ResponseEntity<PeliculasResponseDTO> crearPelicula(@Valid @RequestBody PeliculaRequestDTO crearPeliculaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.crearPelicula(crearPeliculaDTO));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PeliculasResponseDTO> editarPelicula(@Valid @RequestBody PeliculaRequestDTO modificarPeliculaDTO, Long id){
        return ResponseEntity.ok(peliculaService.editarPelicula(modificarPeliculaDTO, id));
    }

    // DELETE FÍSICO, elimina completamente la película de la base de datos
    @DeleteMapping("/eliminar-fisico/{id}")
    public ResponseEntity<Void> eliminarPeliculaFisico(@PathVariable Long id){
        peliculaService.eliminarPeliculaFisico(id);
        return ResponseEntity.noContent().build();
    }

    // DELETE LÓGICO, marca la película como no disponible sin eliminarla de la base de datos
    @DeleteMapping("/eliminar-logico/{id}")
    public ResponseEntity<Void> eliminarPeliculaLogico(@PathVariable Long id){
        peliculaService.eliminarPeliculaLogico(id);
        return ResponseEntity.noContent().build();
    }
}
