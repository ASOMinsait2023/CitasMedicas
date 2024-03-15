package com.minsait.repository;

import com.minsait.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDoctoresRepository  extends JpaRepository<Doctor, Long> {
    List<Doctor> findAllByEspecialidadId(Long especialidadId);
}
