package com.minsait.controller;

import com.minsait.models.Doctor;
import com.minsait.models.Especialidades;
import java.util.Optional;

public class Datos {

    public static Optional<Especialidades> crearEspecialidad() {
        return Optional.of(new Especialidades(1L,"General"));
    }

    public static Optional<Doctor> crearDoctor1() {
        return Optional.of(new Doctor(1L, "ricardo", "azcar", (byte) 1L, crearEspecialidad().get()));
    }

    public static Optional<Doctor> crearDoctor2(){
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        especialidades.setNombre_especialidad("General");
        return Optional.of(new Doctor(2L, "jorge", "cuellar", (byte) 1L, crearEspecialidad().get()));
    }

    public static Optional<Doctor> crearDoctor3(){
        Especialidades especialidades = new Especialidades();
        especialidades.setIdespecialidades(1L);
        especialidades.setNombre_especialidad("General");
        return Optional.of(new Doctor(3L, "exel", "escamilla", (byte) 1L,crearEspecialidad().get() ));
    }

}
