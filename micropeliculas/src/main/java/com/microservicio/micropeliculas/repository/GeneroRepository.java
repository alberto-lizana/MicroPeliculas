package com.microservicio.micropeliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.micropeliculas.model.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{

}
