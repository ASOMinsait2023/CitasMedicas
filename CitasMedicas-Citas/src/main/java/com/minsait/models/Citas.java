package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;


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


    @Column(name= "fechahoracita")
    private LocalDateTime fechaCita;
    //private DateTime fechaCita;
    @Column(name = "estatus")
    private Byte estatus;

    @ManyToOne
    @JoinColumn (name= "idtipocita")
    private TipoCitas idTipoCita;

    @ManyToOne
    @JoinColumn(name= "idpaciente")
    private Paciente idPaciente;
    @ManyToOne
    @JoinColumn(name= "iddoctor")
    private Doctor idDoctor;





}
