package com.microservicio.micropeliculas.service;

import java.util.List;

import com.microservicio.micropeliculas.dto.DirectoresResponseDTO;

public interface DirectorService {

    public List<DirectoresResponseDTO> getAllDirectores();
    public DirectoresResponseDTO getDirectorById(Long id);
    
}
