package com.microservicio.micropeliculas.service;

import java.util.List;

import com.microservicio.micropeliculas.dto.GenerosResponseDTO;


public interface GeneroService {

    public List<GenerosResponseDTO> getAllGeneros();
    public GenerosResponseDTO getGeneroById(Long id);
}
