package com.minsait.controller;

import com.minsait.models.Especialidades;

import java.util.Optional;

public class Datos {

    public static Optional<Especialidades> crearEspecialidad1() {
        return Optional.of(new Especialidades(1L,"General"));
    }

    public static Optional<Especialidades> crearEspecialidad2(){
        return Optional.of(new Especialidades(1L,"General"));
    }

    public static Optional<Especialidades> crearEspecialidad3(){
        return Optional.of(new Especialidades(1L,"General"));
    }

}
