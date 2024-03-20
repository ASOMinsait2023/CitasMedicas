package com.minsait.controller;

import com.minsait.models.*;

import java.time.LocalDateTime;
import java.util.Optional;

public class Datos {


    public static Optional<TipoCitas> crearTipocita1() {
        return Optional.of(new TipoCitas(1L,"General"));
    }

    public static Optional<TipoCitas> crearTipocita2(){
        return Optional.of(new TipoCitas(1L,"General"));
    }

    public static Optional<TipoCitas> crearTipocita3(){
        return Optional.of(new TipoCitas(1L,"General"));
    }

    public static Optional<Especialidades> crearEspecialidad1() {
        return Optional.of(new Especialidades(1L,"General"));
    }

    public static Optional<Especialidades> crearEspecialidad2(){
        return Optional.of(new Especialidades(1L,"General"));
    }

    public static Optional<Especialidades> crearEspecialidad3(){
        return Optional.of(new Especialidades(1L,"General"));
    }

    public static Optional<Doctor> crearDoctor1() {
        return Optional.of(new Doctor(1L, "ricardo", "azcar", (byte) 1L, crearEspecialidad1().get()));
    }

    public static Optional<Doctor> crearDoctor2(){
        return Optional.of(new Doctor(2L, "jorge", "cuellar", (byte) 1L, crearEspecialidad2().get()));
    }

    public static Optional<Doctor> crearDoctor3(){
        return Optional.of(new Doctor(3L, "exel", "escamilla", (byte) 1L,crearEspecialidad3().get() ));
    }

    public static Optional<Paciente> crearPaciente1() {
        return Optional.of(new Paciente(1L, "ricardo", "Azcar", "12345"));
    }

    public static Optional<Paciente> crearPaciente2(){
        return Optional.of(new Paciente(2L, "jorge", "Cuellar","11111"));
    }

    public static Optional<Paciente> crearPaciente3(){
        return Optional.of(new Paciente(3L, "exel", "Escamilla","22222"));
    }


    public static Optional<Citas> crearCita1() {
        LocalDateTime fechaHora = LocalDateTime.of(2024, 3, 12, 1, 18, 0);
        return Optional.of(new Citas(1L, "Requiero una Cita General", (byte) 1L, fechaHora, crearTipocita1().get(),crearPaciente1().get(),crearDoctor1().get()));
    }



}
