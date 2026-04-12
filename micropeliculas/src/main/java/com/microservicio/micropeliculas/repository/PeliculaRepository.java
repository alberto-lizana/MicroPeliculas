package com.microservicio.micropeliculas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.micropeliculas.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    List<Pelicula> findByestaDisponibleTrue();
    List<Pelicula> findByestaDisponibleFalse();
}
