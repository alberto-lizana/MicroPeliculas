package com.microservicio.micropeliculas.service.serviceimp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicio.micropeliculas.dto.DirectoresResponseDTO;
import com.microservicio.micropeliculas.repository.DirectorRepository;
import com.microservicio.micropeliculas.service.DirectorService;


@Service
public class DirectorServiceIMP implements DirectorService {

    private final DirectorRepository directorRepository;

    public DirectorServiceIMP(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }


    @Override
    public List<DirectoresResponseDTO> getAllDirectores() {
        List<DirectoresResponseDTO> directores = directorRepository.findAll()
                                                                   .stream()
                                                                   .map(d -> DirectoresResponseDTO.builder()
                                                                        .idDirector(d.getIdDirector())
                                                                        .nombre(d.getNombre())
                                                                        .build())
                                                                   .toList();
        return directores;    
    }

    @Override
    public DirectoresResponseDTO getDirectorById(Long id) {
        DirectoresResponseDTO director = directorRepository.findById(id)
                                                           .map(d -> DirectoresResponseDTO.builder()
                                                                .idDirector(d.getIdDirector())
                                                                .nombre(d.getNombre())
                                                                .build())
                                                           .orElseThrow(() -> new RuntimeException("Director no encontrado con id: " + id)); 
        return director;
    }

}
