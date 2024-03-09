package com.misait.service;

import com.misait.models.TipoCitas;
import com.misait.repository.ITipoCitasRepository;
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
    public TipoCitas finfById(Long idTipoCita) {
        return iTipoCitasRepository.findById(idTipoCita).orElseThrow();
    }

    @Override
    public TipoCitas saveTC(TipoCitas tipoCitas) {
        if(!validarCadena(tipoCitas.getTipoCita())) throw new IllegalArgumentException();
        return iTipoCitasRepository.save(tipoCitas);
    }

    @Override
    public boolean deleteTipoCitasById(Long idTipoCitas) {
        var tipocita=iTipoCitasRepository.findById(idTipoCitas);
        if(tipocita.isPresent()){
            iTipoCitasRepository.deleteById(idTipoCitas);
        return true;
        }return false;
    }
}
