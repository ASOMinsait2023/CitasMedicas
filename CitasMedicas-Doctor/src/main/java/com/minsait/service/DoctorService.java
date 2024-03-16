package com.minsait.service;

import com.minsait.models.Doctor;
import com.minsait.repository.IDoctoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class DoctorService implements IDoctorService{

    @Autowired
    private IDoctoresRepository doctoresRepository;

    @Autowired
    private static final String REGEX_VALIDACION = "^(?!\s*$)(?!.*\\d)[a-zA-Z\\s]+$";
    private boolean validarCadena(String cadena) {
        return cadena != null && cadena.matches(REGEX_VALIDACION);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctor> findAll() {
        return doctoresRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Doctor findById(Long id) {
        return doctoresRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        if (!validarCadena(doctor.getName()))      throw new IllegalArgumentException();
        if (!validarCadena(doctor.getLastname()))  throw new IllegalArgumentException();
        return doctoresRepository.save(doctor);
    }

    @Override
    @Transactional
    public Doctor update(Doctor doctor) {
        if (validarCadena(doctor.getName()))      throw new IllegalArgumentException();
        if (validarCadena(doctor.getLastname()))  throw new IllegalArgumentException();
        return doctoresRepository.save(doctor);
    }

    @Override
    @Transactional
    public boolean deleteById(Long idDoctor) {
        var paciente = doctoresRepository.findById(idDoctor);
        if (paciente.isPresent()){
            doctoresRepository.deleteById(idDoctor);
            return true;
        }
        return false;
    }

    @Override
    public List<String> findContarEspecialidad(String nombre_especialidad) {
        return doctoresRepository.findContarEspecialidad(nombre_especialidad);
    }

/*
*     @Override
    public List<Doctor> findByIdEspecialidad(Long especialidadId) {
        return doctoresRepository.findAllByEspecialidadId(especialidadId);
    }*/

}
