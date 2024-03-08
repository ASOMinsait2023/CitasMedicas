package com.minsait.service;

import com.minsait.models.Paciente;

import java.util.List;

public interface IPacienteService {

    public List<Paciente> findAll();
    Paciente findById(Long idPasiente);
    Paciente savePasiente(Paciente paciente);
    void deletePaciente(Long pacienteId);
    Paciente updatePaciente(Paciente paciente,Long pacienteId);


}
