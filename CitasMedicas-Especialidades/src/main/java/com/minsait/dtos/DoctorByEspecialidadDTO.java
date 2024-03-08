package com.minsait.dtos;

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
    String nombre_especialidad_D;
    List<DoctorDTO> doctorDTOS;
}
