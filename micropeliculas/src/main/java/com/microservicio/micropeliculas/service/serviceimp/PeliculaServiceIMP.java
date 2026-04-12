package com.microservicio.micropeliculas.service.serviceimp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservicio.micropeliculas.dto.DirectoresResponseDTO;
import com.microservicio.micropeliculas.dto.GenerosResponseDTO;
import com.microservicio.micropeliculas.dto.PeliculaRequestDTO;
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

    public PeliculaServiceIMP(PeliculaRepository peliculaRepository, DirectorRepository directorRepository, 
                              GeneroRepository generoRepository) 
    {
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
                                                                     .estaDisponible(p.getEstaDisponible())
                                                                    
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
                                                                .estaDisponible(p.getEstaDisponible())
                                                            
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
    public List<PeliculasResponseDTO> getAllPeliculasDisponibles() {
        List<PeliculasResponseDTO> p = peliculaRepository.findByestaDisponibleTrue()
                                            .stream()
                                            .map(pa -> PeliculasResponseDTO.builder()
                                                .idPelicula(pa.getId())
                                                .titulo(pa.getTitulo())
                                                .fechaEstreno(pa.getFechaEstreno())
                                                .sinopsis(pa.getSinopsis())
                                                .estaDisponible(pa.getEstaDisponible())
                                                .director(DirectoresResponseDTO.builder()
                                                    .idDirector(pa.getDirector().getIdDirector())
                                                    .nombre(pa.getDirector().getNombre())
                                                    .build())
                                                .genero(GenerosResponseDTO.builder()
                                                    .idGenero(pa.getGenero().getIdGenero())
                                                    .nombre(pa.getGenero().getNombre())
                                                    .build())
                                                .build())
                                             .toList();                                                                                            
        return p;
    }


    @Override
    public List<PeliculasResponseDTO> getAllPeliculasNoDisponibles() {
        List<PeliculasResponseDTO> p = peliculaRepository.findByestaDisponibleFalse()
                                            .stream()
                                            .map(pa -> PeliculasResponseDTO.builder()
                                                .idPelicula(pa.getId())
                                                .titulo(pa.getTitulo())
                                                .fechaEstreno(pa.getFechaEstreno())
                                                .sinopsis(pa.getSinopsis())
                                                .estaDisponible(pa.getEstaDisponible())

                                                .director(DirectoresResponseDTO.builder()
                                                    .idDirector(pa.getDirector().getIdDirector())
                                                    .nombre(pa.getDirector().getNombre())
                                                    .build())

                                                .genero(GenerosResponseDTO.builder()
                                                    .idGenero(pa.getGenero().getIdGenero())
                                                    .nombre(pa.getGenero().getNombre())
                                                    .build())
                                                .build())
                                            .toList();
        return p;
    }


    @Override
    public PeliculasResponseDTO crearPelicula(PeliculaRequestDTO crearPeliculaDTO) {

        Director d = obtenerDirectorPorId(crearPeliculaDTO.getIdDirector());

        Genero g = obtenerGeneroPorId(crearPeliculaDTO.getIdGenero()); 

        Pelicula p = Pelicula.builder()
                                 .titulo(crearPeliculaDTO.getTitulo().trim().toUpperCase())
                                 .fechaEstreno(crearPeliculaDTO.getFechaEstreno())
                                 .sinopsis(crearPeliculaDTO.getSinopsis().toUpperCase().trim())
                                 .estaDisponible(true)
                                 .director(d)
                                 .genero(g)
                                 .build();

        Pelicula guardada = peliculaRepository.save(p);

        PeliculasResponseDTO response = PeliculasResponseDTO.builder()
                                            .idPelicula(guardada.getId())
                                            .titulo(guardada.getTitulo())
                                            .fechaEstreno(guardada.getFechaEstreno())
                                            .sinopsis(guardada.getSinopsis())
                                            .estaDisponible(guardada.getEstaDisponible())
                                            
                                            .director(DirectoresResponseDTO.builder()
                                                .idDirector(guardada.getDirector().getIdDirector())
                                                .nombre(guardada.getDirector().getNombre())
                                                .build())
                                            
                                            .genero(GenerosResponseDTO.builder()
                                                .idGenero(guardada.getGenero().getIdGenero())
                                                .nombre(guardada.getGenero().getNombre())
                                                .build())
                                            
                                            .build();
        return response;    
    }

 
    @Override
    public PeliculasResponseDTO editarPelicula(PeliculaRequestDTO modificarPeliculaDTO, Long id) {

        Pelicula peliculaExistente = obtenerPeliculaPorId(id);

        Director d = obtenerDirectorPorId(modificarPeliculaDTO.getIdDirector());

        Genero g = obtenerGeneroPorId(modificarPeliculaDTO.getIdGenero());

        peliculaExistente.setTitulo(modificarPeliculaDTO.getTitulo().trim().toUpperCase());
        peliculaExistente.setFechaEstreno(modificarPeliculaDTO.getFechaEstreno());
        peliculaExistente.setSinopsis(modificarPeliculaDTO.getSinopsis().toUpperCase().trim());
        peliculaExistente.setDirector(d);
        peliculaExistente.setGenero(g);

        Pelicula peliculaModificada = peliculaRepository.save(peliculaExistente);

        PeliculasResponseDTO response = PeliculasResponseDTO.builder()
                                            .idPelicula(peliculaModificada.getId())
                                            .titulo(peliculaModificada.getTitulo())
                                            .fechaEstreno(peliculaModificada.getFechaEstreno())
                                            .sinopsis(peliculaModificada.getSinopsis())
                                            .estaDisponible(peliculaModificada.getEstaDisponible())
                                            
                                            .director(DirectoresResponseDTO.builder()
                                                .idDirector(peliculaModificada.getDirector().getIdDirector())
                                                .nombre(peliculaModificada.getDirector().getNombre())
                                                .build())
                                            
                                            .genero(GenerosResponseDTO.builder()
                                                .idGenero(peliculaModificada.getGenero().getIdGenero())
                                                .nombre(peliculaModificada.getGenero().getNombre())
                                                .build())
                                            
                                            .build();
        return response;
    }


    @Override
    public void eliminarPeliculaFisico(Long id) {
        Pelicula p = obtenerPeliculaPorId(id);
        peliculaRepository.delete(p);
    }


    @Override
    public void eliminarPeliculaLogico(Long id) {
        Pelicula p = obtenerPeliculaPorId(id);
        p.setEstaDisponible(false);
        peliculaRepository.save(p);
    }

    
    // Métodos auxiliares para obtener entidades por ID
    public Director obtenerDirectorPorId(Long idDirector) {
        return directorRepository.findById(idDirector)
                                 .orElseThrow(() -> new RuntimeException("Director no encontrado con id: " + idDirector));
    }

    public Genero obtenerGeneroPorId(Long idGenero) {
        return generoRepository.findById(idGenero)
                                   .orElseThrow(() -> new RuntimeException("Genero no encontrado con id: " + idGenero));
    }    

    public Pelicula obtenerPeliculaPorId(Long idPelicula) {
        return peliculaRepository.findById(idPelicula)
                                 .orElseThrow(() -> new RuntimeException("Pelicula no encontrada con id: " + idPelicula));
    }
}