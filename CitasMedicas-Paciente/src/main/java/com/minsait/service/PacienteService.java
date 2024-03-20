package com.minsait.service;

import com.minsait.models.Paciente;
import com.minsait.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private IPacienteRepository  pacienteRepository;

    @Autowired
    private static final String REGEX_VALIDACION = "^(?!\s*$)(?!.*\\d)[a-zA-Z\\s]+$";
    @Autowired
    private static final String REGEX_NSS = "^\\d{5}$";
    private boolean validarCadena(String cadena) {
        return cadena != null && cadena.matches(REGEX_VALIDACION);
    }
    private boolean validarNSS(String cadena) {
        return cadena != null && cadena.matches(REGEX_NSS);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Paciente save(Paciente paciente) {
        if (!validarCadena(paciente.getName()))      throw new IllegalArgumentException();
        if (!validarCadena(paciente.getLastname()))  throw new IllegalArgumentException();
        if (!validarNSS(paciente.getNss()))          throw new IllegalArgumentException();
        return pacienteRepository.save(paciente);
    }

    @Override
    @Transactional
    public Paciente update(Paciente paciente) {
        if (validarCadena(paciente.getName()))      throw new IllegalArgumentException();
        if (validarCadena(paciente.getLastname()))  throw new IllegalArgumentException();
        if (validarNSS(paciente.getNss()))          throw new IllegalArgumentException();
        return pacienteRepository.save(paciente);
    }

    @Override
    @Transactional
    public boolean deleteById(Long pacintesId) {
        var paciente = pacienteRepository.findById(pacintesId);
        if (paciente.isPresent()){
            pacienteRepository.deleteById(pacintesId);
            return true;
        }
        return false;
    }




}
