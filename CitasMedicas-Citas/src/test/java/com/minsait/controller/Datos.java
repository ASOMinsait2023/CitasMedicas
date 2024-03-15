package com.minsait.controller;

import com.minsait.models.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class Datos {


    public static Optional<TipoCitas> crearTipodeCita() {
        return Optional.of(new TipoCitas(1L,"General"));
    }
    public static Optional<Especialidades> crearEspecialidad() {
        return Optional.of(new Especialidades(1L,"General"));
    }

    public static Optional<Doctor> crearDoctor1() {
        return Optional.of(new Doctor(1L, "ricardo", "azcar", (byte) 1L, crearEspecialidad().get()));
    }

    public static Optional<Paciente> crearPaciente1() {
        return Optional.of(new Paciente(1L, "ricardo", "Azcar", "12345"));
    }


    public static Optional<Citas> crearCita1() {
        LocalDateTime fechaHora = LocalDateTime.of(2024, 3, 12, 1, 18, 0);
        return Optional.of(new Citas(1L, "Requiero una Cita General", (byte) 1L, fechaHora, crearTipodeCita().get(),crearPaciente1().get(),crearDoctor1().get()));
    }



}
