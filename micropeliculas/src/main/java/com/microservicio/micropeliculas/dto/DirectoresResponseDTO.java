package com.microservicio.micropeliculas.dto;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class DirectoresResponseDTO {

    private Long idDirector;
    private String nombre;
    
}
