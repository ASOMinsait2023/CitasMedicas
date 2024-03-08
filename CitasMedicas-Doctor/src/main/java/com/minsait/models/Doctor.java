package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctores")
public class Doctor {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "iddoctor")
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellidos")
    private String lastname;
    @Column(name = "estatus")
    private byte status;

    @ManyToOne
    @JoinColumn(name = "idespecialidad")
    private Especialidades idespecialidad;


}
