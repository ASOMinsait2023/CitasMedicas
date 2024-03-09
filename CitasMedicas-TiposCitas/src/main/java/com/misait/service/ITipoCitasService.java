package com.misait.service;

import com.misait.models.TipoCitas;

import java.util.List;

public interface ITipoCitasService {
    List<TipoCitas> findAll();
    TipoCitas finfById(Long idTipoCita);
    TipoCitas saveTC(TipoCitas tipoCitas);
    boolean deleteTipoCitasById (Long idTipoCitas);
}
