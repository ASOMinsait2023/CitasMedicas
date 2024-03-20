package com.minsait.service;

import com.minsait.dtos.EspecialidadesByDoctorDTO;
import com.minsait.models.Doctor;

import java.util.List;

public interface IDoctorService {

    List<Doctor> findAll();

    Doctor findById(Long id);

    Doctor save (Doctor doctor);

    Doctor update (Doctor doctor);

    boolean deleteById(Long idDoctor);

    EspecialidadesByDoctorDTO findEspecialidadesByDoctor (Long idDoctor);




}
