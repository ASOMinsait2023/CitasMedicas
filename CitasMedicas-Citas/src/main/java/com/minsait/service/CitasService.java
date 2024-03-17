package com.minsait.service;

import com.minsait.models.Citas;
import com.minsait.repository.ICitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CitasService implements ICitasService {

    @Autowired
    private ICitasRepository citasRepository;

    @Autowired
    private static final String REGEX_VALIDACION = "^(?!\s*$)(?!.*\\d)[a-zA-Z\\s]+$";

    private boolean validarCadena(String cadena) {
        return cadena != null && cadena.matches(REGEX_VALIDACION);
    }

    @Override
    public List<Citas> findAll() {
        return citasRepository.findAll();
    }

    @Override
    public Citas findById(Long idCitas) {
        return citasRepository.findById(idCitas).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    @Override
    public Citas save(Citas citas) {
        if (!validarCadena(citas.getDescripcion()))   throw new IllegalArgumentException();
        return citasRepository.save(citas);
    }

    @Override
    public Citas update(Citas citas) {
        if (validarCadena(citas.getDescripcion()))   throw new IllegalArgumentException();
        return citasRepository.save(citas);
    }

    @Override
    public boolean deleteById(Long idCitas) {
        var citas = citasRepository.findById(idCitas);
        if (citas.isPresent()){
            citasRepository.deleteById(idCitas);
            return true;
        }
        return false;
    }

    @Override
    public List<Citas> findCitasFechas() {
        return citasRepository.findCitasFechas();
    }

    @Override
    public List<String> fincontar() {
        return citasRepository.fincontar();
    }

    @Override
    public List<Citas> findCitasFechasEspe(String fecha) {
        return citasRepository.findCitasFechasEspe(fecha);
    }


}
