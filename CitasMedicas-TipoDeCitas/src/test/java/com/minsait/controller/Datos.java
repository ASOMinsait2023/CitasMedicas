package com.minsait.controller;

import com.minsait.models.TipoCitas;

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

}
