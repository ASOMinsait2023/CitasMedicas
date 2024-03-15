package com.minsait.dtos;

import com.minsait.models.Especialidades;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String name;
    private String lastname;
    private Byte status;
    private Especialidades idespecialidad;
}
