package com.minsait.service;

import com.minsait.models.Citas;

import java.util.List;

public interface ICitasService {
    List<Citas> findAll();
    Citas findById(Long idCitas);
    Citas save (Citas citas);

    Citas update (Citas citas);

    boolean deleteById(Long idCitas);
}
