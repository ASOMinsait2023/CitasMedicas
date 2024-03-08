package com.minsait.dtos;

import com.minsait.models.Especialidades;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class DoctorDTO {
    private Long idD;
    private String nombre;
    private String apellidos;
    private byte estatus;
    private Especialidades idespecialidad;
}
