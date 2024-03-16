package com.minsait.repository;

import com.minsait.models.Especialidades;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadRepository extends JpaRepository<Especialidades,Long> {

//    Optional<Especialidades> findById(Long idEspecialidad);
@Query(value = "select COUNT(*) from doctores as d inner join especialidades as e on d.idespecialidad=e.idespecialidad where e.nombre_especialidad=?1",nativeQuery = true)
List<String> findContarEspecialidad(@PathVariable String nombre_especialidad);
}
