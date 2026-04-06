package com.microservicio.micropeliculas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="directores")
@AllArgsConstructor 
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter 
@Setter
@Builder

public class Director {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long idDirector;

    @Column(name="nombre", nullable=false, length=100, unique=true)
    private String nombre;

}
