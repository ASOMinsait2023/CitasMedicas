package com.minsait.dtos;

import com.minsait.models.Especialidades;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EspecialidadesByDoctorDTO {

    String idEspecialidad;
    String Especialidad;
    List<EspecialidadesDTO> especialidadesDTOS;



}
