package com.microservicio.micropeliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.micropeliculas.model.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long>{

}
