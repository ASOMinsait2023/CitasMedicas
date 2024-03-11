package com.minsait.service;

import com.minsait.models.Paciente;

import java.util.List;

public interface IPacienteService {

    List<Paciente> findAll();
    Paciente findById(Long id);
    Paciente save (Paciente paciente);
    Paciente update (Paciente paciente);
    boolean deleteById(Long pacintesId);


}
