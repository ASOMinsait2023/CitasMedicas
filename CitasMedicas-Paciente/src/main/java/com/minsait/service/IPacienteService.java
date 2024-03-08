package com.minsait.service;

import com.minsait.models.Paciente;

import java.util.List;

public interface IPacienteService {

    List<Paciente> findAll();

    Paciente findById(Long id);

    Paciente save (Paciente paciente);

    Paciente update (Paciente paciente);

    // List<Paciente> findByIdConsulta(Long consultasId);

    boolean deleteById(Long pacintesId);

}
