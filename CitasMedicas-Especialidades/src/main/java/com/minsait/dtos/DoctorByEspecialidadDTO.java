package com.minsait.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorByEspecialidadDTO {
    Long idespecialidades;
    String nombre_especialidad;
    List<DoctorDTO> doctorDTOS;
}
