package com.misait.service;

import com.misait.models.TipoCitas;

import java.util.List;

public interface ITipoCitasService {
    List<TipoCitas> findAll();
    TipoCitas findById(Long idTipoCitas);
    TipoCitas save(TipoCitas tipoCitas);
    boolean deleteById(Long idTipoCitas);
}
