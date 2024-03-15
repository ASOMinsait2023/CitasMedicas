package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "idpaciente")
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "apellidos")
    private String lastname;
    private String nss;



}
