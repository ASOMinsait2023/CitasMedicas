package com.minsait.service;

import com.minsait.models.TipoCitas;

import java.util.List;

public interface ITipoCitasService {
    List<TipoCitas> findAll();

    TipoCitas findById(Long idTipoCitas);

    TipoCitas save(TipoCitas tipoCitas);

    TipoCitas update (TipoCitas tipoCitas);

    boolean deleteById(Long idTipoCitas);
}
