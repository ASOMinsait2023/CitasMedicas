package com.minsait.service;

import com.minsait.models.TipoCitas;
import com.minsait.repository.ITipoCitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class TipoCitasService implements ITipoCitasService{
    @Autowired
    private ITipoCitasRepository iTipoCitasRepository;
    @Autowired
    private static final String REGEX_VALIDACION = "^(?!\s*$)(?!.*\\d)[a-zA-Z\\s]+$";
    private boolean validarCadena(String cadena) {
        return cadena != null && cadena.matches(REGEX_VALIDACION);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoCitas> findAll() {
        return iTipoCitasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TipoCitas findById(Long idTipoCitas) {
        return iTipoCitasRepository.findById(idTipoCitas).orElseThrow();
    }

    @Override
    @Transactional
    public TipoCitas save(TipoCitas tipoCitas) {
        if(!validarCadena(tipoCitas.getTipo_cita()))throw  new IllegalArgumentException();
        return iTipoCitasRepository.save(tipoCitas);
    }

    @Override
    @Transactional
    public TipoCitas update(TipoCitas tipoCitas) {
        if (validarCadena(tipoCitas.getTipo_cita()))          throw new IllegalArgumentException();
        return iTipoCitasRepository.save(tipoCitas);
    }

    @Override
    @Transactional
    public boolean deleteById(Long idTipoCitas) {
        var tipoCitas = iTipoCitasRepository.findById(idTipoCitas);
        if (tipoCitas.isPresent()){
            iTipoCitasRepository.deleteById(idTipoCitas);
            return true;
        }
        return false;
    }
}
