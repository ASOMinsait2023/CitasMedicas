package com.minsait.service;

import com.minsait.models.Paciente;
import com.minsait.repository.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PacienteService implements IPacienteService{

    @Autowired
    private IPacienteRepository  pacienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Paciente findById(Long idPasiente) {
        return pacienteRepository.findById(idPasiente).orElseThrow();
    }

    @Override
    @Transactional
    public Paciente savePasiente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    @Transactional
    public void deletePaciente(Long pacienteId) {
        var paciente=pacienteRepository.findById(pacienteId);
        if(paciente.isPresent()){
            pacienteRepository.deleteById(pacienteId);
        }

    }

    @Override
    @Transactional
    public Paciente updatePaciente(Paciente paciente, Long pacienteId) {
        Paciente pacBD=pacienteRepository.findById(pacienteId).get();

        if(Objects.nonNull(paciente.getName()) && paciente.getName().matches("^(?!\\\\s*$)(?!.*\\\\d)[a-zA-Z]+")){
            pacBD.setName(paciente.getName());
        }
        if(Objects.nonNull(paciente.getLastname()) &&
        paciente.getLastname().matches("^(?!\\\\s*$)(?!.*\\\\d)[a-zA-Z]+")){
            pacBD.setLastname(paciente.getLastname());
        }
        if(Objects.nonNull(paciente.getNss()) &&
        paciente.getNss().matches("")){
            pacBD.setNss(paciente.getNss());
        }
        return pacienteRepository.save(pacBD);
    }


}
