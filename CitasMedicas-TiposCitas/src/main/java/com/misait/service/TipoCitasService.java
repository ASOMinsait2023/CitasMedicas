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
    public TipoCitas finfById(Long idTipoCita) {
        return null;
    }

    @Override
    public TipoCitas saveTC(TipoCitas tipoCitas) {
        return null;
    }

    @Override
    public boolean deleteTipoCitasById(Long idTipoCitas) {
        return false;
    }
}
