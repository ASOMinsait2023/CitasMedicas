package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estatus")
    private Byte status;
    @Column(name = "fechahoracita")
    private LocalDateTime fechaCita;

    @ManyToOne
    @JoinColumn(name = "idtipocita")
    private TipoCitas idTipoCita;

    @ManyToOne
    @JoinColumn(name = "idpaciente")
    private Paciente idPaciente;
    @ManyToOne
    @JoinColumn(name = "iddoctor")
    private Doctor idDoctor;


}
