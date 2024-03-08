package com.minsait.service;

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
        //Especialidades espeBD=especialidadRepository.save(especialidades).setNombre_especialidad();
        return especialidadRepository.save(especialidades);
    }

    @Override
    @Transactional
    public boolean deleteEspecialidadById(Long idespecialidad) {
var especialida=especialidadRepository.findById(idespecialidad);
if(especialida.isPresent()){
        especialidadRepository.deleteById(idespecialidad);
return true;
}
return false;
    }

    @Override
    public Optional<Especialidades> findByEspecialidades(Long idEspecialidad) {
        return especialidadRepository.findById(idEspecialidad);
    }

}
