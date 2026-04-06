package com.microservicio.micropeliculas.service.serviceimp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicio.micropeliculas.dto.GenerosResponseDTO;
import com.microservicio.micropeliculas.repository.GeneroRepository;
import com.microservicio.micropeliculas.service.GeneroService;

@Service
public class GeneroServiceIMP implements GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroServiceIMP(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public List<GenerosResponseDTO> getAllGeneros() {
        List<GenerosResponseDTO> generos = generoRepository.findAll()
                                                           .stream()
                                                           .map(g -> GenerosResponseDTO.builder()
                                                                .idGenero(g.getIdGenero())
                                                                .nombre(g.getNombre())
                                                                .build())
                                                           .toList();
        return generos;
    }


    @Override
    public GenerosResponseDTO getGeneroById(Long id) {
        GenerosResponseDTO genero = generoRepository.findById(id)
                                                    .map(g -> GenerosResponseDTO.builder()
                                                        .idGenero(g.getIdGenero())
                                                        .nombre(g.getNombre())
                                                        .build())
                                                    .orElseThrow(() -> new RuntimeException("Genero no encontrado con id: " + id));
                                               
        return genero;
    }
}
