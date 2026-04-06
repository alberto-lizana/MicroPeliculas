package com.microservicio.micropeliculas.service.serviceimp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicio.micropeliculas.dto.CrearPeliculaDTO;
import com.microservicio.micropeliculas.dto.DirectoresResponseDTO;
import com.microservicio.micropeliculas.dto.GenerosResponseDTO;
import com.microservicio.micropeliculas.dto.PeliculasResponseDTO;
import com.microservicio.micropeliculas.model.Director;
import com.microservicio.micropeliculas.model.Genero;
import com.microservicio.micropeliculas.model.Pelicula;
import com.microservicio.micropeliculas.repository.DirectorRepository;
import com.microservicio.micropeliculas.repository.GeneroRepository;
import com.microservicio.micropeliculas.repository.PeliculaRepository;
import com.microservicio.micropeliculas.service.PeliculaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PeliculaServiceIMP implements PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final DirectorRepository directorRepository;
    private final GeneroRepository generoRepository;

    public PeliculaServiceIMP(PeliculaRepository peliculaRepository, DirectorRepository directorRepository, GeneroRepository generoRepository) {
        this.peliculaRepository = peliculaRepository;
        this.directorRepository = directorRepository;
        this.generoRepository = generoRepository;
    }


    @Override
    public List<PeliculasResponseDTO> getAllPeliculas() {
        List<PeliculasResponseDTO> peliculas = peliculaRepository.findAll()
                                                                 .stream()
                                                                 .map(p -> PeliculasResponseDTO.builder()
                                                                     .idPelicula(p.getId())
                                                                     .titulo(p.getTitulo())
                                                                     .fechaEstreno(p.getFechaEstreno())
                                                                     .sinopsis(p.getSinopsis())
                                                                    
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
                                                                .sinopsis(p.getSinopsis())
                                                            
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

    @Override
    public PeliculasResponseDTO crearPelicula(CrearPeliculaDTO crearPeliculaDTO) {

        Director d = directorRepository.findById(crearPeliculaDTO.getIdDirector())
                                       .orElseThrow(() -> new RuntimeException("Director no encontrado"));

        Genero g = generoRepository.findById(crearPeliculaDTO.getIdGenero())
                                   .orElseThrow(() -> new RuntimeException("Genero no encontrado"));

        Pelicula p = Pelicula.builder()
                                 .titulo(crearPeliculaDTO.getTitulo().trim().toUpperCase())
                                 .fechaEstreno(crearPeliculaDTO.getFechaEstreno())
                                 .sinopsis(crearPeliculaDTO.getSinopsis().toUpperCase())
                                 .director(d)
                                 .genero(g)
                                 .build();

        Pelicula guardada = peliculaRepository.save(p);

        PeliculasResponseDTO response = PeliculasResponseDTO.builder()
                                            .idPelicula(guardada.getId())
                                            .titulo(guardada.getTitulo())
                                            .fechaEstreno(guardada.getFechaEstreno())
                                            .sinopsis(guardada.getSinopsis())
                                            
                                            .director(DirectoresResponseDTO.builder()
                                                .idDirector(d.getIdDirector())
                                                .nombre(d.getNombre())
                                                .build())
                                            
                                            .genero(GenerosResponseDTO.builder()
                                                .idGenero(g.getIdGenero())
                                                .nombre(g.getNombre())
                                                .build())
                                            
                                            .build();
        return response;    
    }

}