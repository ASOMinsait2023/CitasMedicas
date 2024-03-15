package com.minsait.service;

import com.minsait.client.IDoctorCliente;
import com.minsait.dtos.DotorByEspecialidadDTOS;
import com.minsait.models.Especialidades;
import com.minsait.repository.IEspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements IEspecialidadService{
    @Autowired
    private IEspecialidadRepository especialidadRepository;
    @Autowired
    private IDoctorCliente iDoctorCliente;
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
    public boolean deleteEspecialidadById(Long idEspecialidad) {
        var especialida = especialidadRepository.findById(idEspecialidad);
        if (especialida.isPresent()) {
            especialidadRepository.deleteById(idEspecialidad);
            return true;
        }
        return false;
    }

    @Override
    public DotorByEspecialidadDTOS findDoctorByEspecialidadId(Long especialidadId) {
       var espe=especialidadRepository.findById(especialidadId).orElseThrow();
       var doctorDTOList=iDoctorCliente.findByIdEspecialidad(especialidadId);

        return DotorByEspecialidadDTOS.builder()
                .nombre_especialidadDTO(espe.getNombre_especialidad())
                .doctorDTOS(doctorDTOList)
                .build();
    }



}
