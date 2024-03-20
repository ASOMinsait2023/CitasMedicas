package com.minsait.service;

import com.minsait.models.Especialidades;


import java.util.List;
import java.util.Optional;

public interface IEspecialidadService {
    List<Especialidades> findAll();
    Especialidades findById(Long idEspecialidad);
     Especialidades save(Especialidades especialidades);
     boolean deleteEspecialidadById(Long idEspecialidad);
//DoctorByEspecialidadDTO findDoctorByEspecialidadId(Long idEspecialidad);














}
