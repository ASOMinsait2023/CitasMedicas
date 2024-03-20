package com.minsait.repository;

import com.minsait.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IDoctoresRepository  extends JpaRepository<Doctor, Long> {
    //List<Doctor> findAllByEspecialidadId(Long especialidadId);
    @Query(value = " select COUNT(*) from doctores  where idEspecialidad=?1",nativeQuery = true)
    List<String> findContarEspecialidad( Long idEspecialidad);
}
