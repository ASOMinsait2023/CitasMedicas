package com.misait.service;

import com.misait.models.Doctores;
import com.misait.repository.IDoctoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctoresService implements IDoctoresService {
    @Autowired
    private IDoctoresRepository doctoresRepository;
    @Autowired
    private static final String REGEX_VALIDACION = "^(?!\s*$)(?!.*\\d)[a-zA-Z\\s]+$";
    private boolean validarCadena(String cadena) {
        return cadena != null && cadena.matches(REGEX_VALIDACION);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Doctores> findAll() {
        return doctoresRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Doctores findById(Long idDoctor) {
        return doctoresRepository.findById(idDoctor).orElseThrow();
    }

    @Override
    @Transactional
    public Doctores save(Doctores doctores) {
        if (!validarCadena(doctores.getName()))throw new IllegalArgumentException();
        if (!validarCadena(doctores.getLastname()))throw new IllegalArgumentException();
        return doctoresRepository.save(doctores);
    }

    @Override
    @Transactional
    public boolean deleteDoctorById(Long idDoctor) {
        var doc=doctoresRepository.findById(idDoctor);
        if (doc.isPresent()){
        doctoresRepository.deleteById(idDoctor);
            return true;
        }
        return false;
    }
}
