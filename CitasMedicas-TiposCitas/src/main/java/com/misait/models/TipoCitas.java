package com.misait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.MediaSize;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipodecitas")
public class TipoCitas {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "idtipocita")
    private Long idTC;
    @Column(name = "tipo_cita")
    private String tipoCita;
}
