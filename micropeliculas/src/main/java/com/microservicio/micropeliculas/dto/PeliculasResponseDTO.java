package com.microservicio.micropeliculas.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PeliculasResponseDTO {

    private Long idPelicula;
    private String titulo;
    private LocalDate fechaEstreno;
    private GenerosResponseDTO genero;
    private DirectoresResponseDTO director;

}
