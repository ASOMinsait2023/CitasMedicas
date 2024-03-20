package com.minsait.dtos;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EspecialidadesDTO {
    @ManyToOne
    @JoinColumn(name = "idespecialidad")
    private Long idespecialidades;
    private String nombre_especialidad;

}
