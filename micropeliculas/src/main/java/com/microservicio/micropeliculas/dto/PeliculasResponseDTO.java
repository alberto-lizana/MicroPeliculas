package com.microservicio.micropeliculas.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String sinopsis;
    private Boolean estaDisponible;
    private GenerosResponseDTO genero;
    private DirectoresResponseDTO director;

}
