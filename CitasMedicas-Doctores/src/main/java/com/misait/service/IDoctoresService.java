package com.misait.service;

import com.misait.models.Doctores;

import java.util.List;

public interface IDoctoresService {
    List<Doctores> findAll();
    Doctores findById(Long idDoctor);
    Doctores save(Doctores doctores);
    boolean deleteDoctorById(Long idDoctor);
}
