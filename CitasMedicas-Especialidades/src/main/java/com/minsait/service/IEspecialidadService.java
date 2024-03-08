package com.minsait.service;

import com.minsait.dtos.DoctorByEspecialidadDTO;
import com.minsait.models.Especialidades;
import lombok.extern.java.Log;

import java.util.List;

public interface IEspecialidadService {
    public List<Especialidades> findAll();
    Especialidades findById(Long idEspecialidad);
     Especialidades save(Especialidades especialidades);
     boolean deleteEspecialidadById(Long idespecialidad);
   DoctorByEspecialidadDTO findDoctoresByEspecialidadesIdI(Long idEspecialidad);














}
