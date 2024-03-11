package com.minsait.service;


import com.minsait.models.Especialidades;
import com.minsait.repository.IEspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class EspecialidadService implements IEspecialidadService{
    @Autowired
    private IEspecialidadRepository especialidadRepository;

    @Autowired
    private static final String REGEX_VALIDACION = "^(?!\s*$)(?!.*\\d)[a-zA-Z\\s]+$";
    private boolean validarCadena(String cadena) {
        return cadena != null && cadena.matches(REGEX_VALIDACION);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Especialidades> findAll() {
        return especialidadRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Especialidades findById(Long idEspecialidad) {
        return especialidadRepository.findById(idEspecialidad).orElseThrow();
    }

    @Override
    @Transactional
    public Especialidades save(Especialidades especialidades) {
        if (!validarCadena(especialidades.getNombre_especialidad())) throw new IllegalArgumentException();
        return especialidadRepository.save(especialidades);
    }

    @Override
    @Transactional
    public boolean deleteEspecialidadById(Long idespecialidad) {
        var especialida = especialidadRepository.findById(idespecialidad);
        if (especialida.isPresent()) {
            especialidadRepository.deleteById(idespecialidad);
            return true;
        }
        return false;
    }




}
