package com.microservicio.micropeliculas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="peliculas")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Setter
@Builder

public class Pelicula {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="titulo", nullable=false, length=200, unique=true)
    private String titulo;

    @Column(name="fecha_estreno", nullable=false)
    private LocalDate fechaEstreno;

    @Column(name="sinopsis", nullable=true, length=1000)
    private String sinopsis;

    @Column(name="esta_disponible", nullable=false)
    private Boolean estaDisponible;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idDirector", nullable=false)
    private Director director;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idGenero", nullable=false)
    private Genero genero;

}
