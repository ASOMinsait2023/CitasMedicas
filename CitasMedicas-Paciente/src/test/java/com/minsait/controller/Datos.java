package com.minsait.controller;

import com.minsait.models.Paciente;
import java.util.Optional;

public class Datos {

    public static Optional<Paciente> crearPaciente1() {
        return Optional.of(new Paciente(1L, "ricardo", "Azcar", "12345"));
    }

    public static Optional<Paciente> crearPaciente2(){
        return Optional.of(new Paciente(2L, "jorge", "Cuellar","11111"));
    }

    public static Optional<Paciente> crearPaciente3(){
        return Optional.of(new Paciente(3L, "exel", "Escamilla","22222"));
    }

}
