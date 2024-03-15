package com.minsait.repository;

import com.minsait.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDoctoresRepository  extends JpaRepository<Doctor, Long> {
    //List<Doctor> findAllByEspecialidadId(Long especialidadId);
}
