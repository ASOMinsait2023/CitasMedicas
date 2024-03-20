package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipodecitas")
public class TipoCitas {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "idtipocita")
    private Long idtipocita;
    @Column(name = "tipo_cita")
    private String tipo_cita;
}
