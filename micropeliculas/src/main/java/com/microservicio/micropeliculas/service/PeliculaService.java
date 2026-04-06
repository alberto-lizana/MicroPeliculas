package com.microservicio.micropeliculas.service;

import java.util.List;

import com.microservicio.micropeliculas.dto.CrearPeliculaDTO;
import com.microservicio.micropeliculas.dto.PeliculasResponseDTO;

public interface PeliculaService {

    public List<PeliculasResponseDTO> getAllPeliculas();
    public PeliculasResponseDTO getPeliculaById(Long id);
    public PeliculasResponseDTO crearPelicula(CrearPeliculaDTO crearPeliculaDTO);
}
