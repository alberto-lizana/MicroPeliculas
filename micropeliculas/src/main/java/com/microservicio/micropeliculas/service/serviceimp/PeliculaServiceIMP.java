package com.microservicio.micropeliculas.service.serviceimp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicio.micropeliculas.dto.DirectoresResponseDTO;
import com.microservicio.micropeliculas.dto.GenerosResponseDTO;
import com.microservicio.micropeliculas.dto.PeliculasResponseDTO;
import com.microservicio.micropeliculas.repository.PeliculaRepository;
import com.microservicio.micropeliculas.service.PeliculaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PeliculaServiceIMP implements PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public PeliculaServiceIMP(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }


    @Override
    public List<PeliculasResponseDTO> getAllPeliculas() {
        List<PeliculasResponseDTO> peliculas = peliculaRepository.findAll()
                                                                 .stream()
                                                                 .map(p -> PeliculasResponseDTO.builder()
                                                                     .idPelicula(p.getId())
                                                                     .titulo(p.getTitulo())
                                                                     .fechaEstreno(p.getFechaEstreno())
                                                                    
                                                                     .director(DirectoresResponseDTO.builder()
                                                                         .idDirector(p.getDirector().getIdDirector())
                                                                         .nombre(p.getDirector().getNombre())
                                                                         .build())

                                                                     .genero(GenerosResponseDTO.builder()
                                                                         .idGenero(p.getGenero().getIdGenero())
                                                                         .nombre(p.getGenero().getNombre())
                                                                         .build())
                                                                 
                                                                 .build())
                                                                 .toList();
        return peliculas;
    }

    @Override
    public PeliculasResponseDTO getPeliculaById(Long id) {
        PeliculasResponseDTO pelicula = peliculaRepository.findById(id)
                                                          .map(p -> PeliculasResponseDTO.builder()
                                                                .idPelicula(p.getId())
                                                                .titulo(p.getTitulo())
                                                                .fechaEstreno(p.getFechaEstreno())
                                                            
                                                                .director(DirectoresResponseDTO.builder()
                                                                    .idDirector(p.getDirector().getIdDirector())
                                                                    .nombre(p.getDirector().getNombre())
                                                                    .build())
                                                            
                                                                .genero(GenerosResponseDTO.builder()
                                                                    .idGenero(p.getGenero().getIdGenero())
                                                                    .nombre(p.getGenero().getNombre())
                                                                    .build())
                                                          
                                                          .build())
                                                          .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con id: " + id));
        return pelicula;
    }

}
