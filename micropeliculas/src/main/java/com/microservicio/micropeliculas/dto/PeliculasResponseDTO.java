package com.microservicio.micropeliculas.dto;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime fechaEstreno;
    private GenerosResponseDTO genero;
    private DirectoresResponseDTO director;

}
