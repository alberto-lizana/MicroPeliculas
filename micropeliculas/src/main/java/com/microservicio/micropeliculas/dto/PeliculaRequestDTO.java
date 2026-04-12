package com.microservicio.micropeliculas.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

public class PeliculaRequestDTO {

    @Size(min=1, max=100, message="El título debe tener entre 1 y 100 caracteres")
    private String titulo;
    
    @NotNull(message="La fecha de estreno no puede ser nula")
    private LocalDate fechaEstreno;

    @Size(max=1000, message="La sinopsis no puede tener más de 1000 caracteres")
    private String sinopsis;

    private Boolean estaDisponible;
        
    @NotNull(message="El id del género no puede ser nulo")
    private Long idGenero;
    
    @NotNull(message="El id del director no puede ser nulo")
    private Long idDirector;

}



