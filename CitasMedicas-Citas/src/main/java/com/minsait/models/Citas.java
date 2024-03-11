package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "citas")
public class Citas {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "idcita")
    private Long idCitas;
    @Column(name= "descripcion")
    private String descripcion;
    @Column(name= "idtipocita")
    private String idTipoCita;
    @Column(name= "idpaciente")
    private String idPaciente;
    @Column(name= "iddoctor")
    private String idDoctor;



}
