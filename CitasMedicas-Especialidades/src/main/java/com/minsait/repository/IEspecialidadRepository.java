package com.minsait.repository;

import com.minsait.models.Especialidades;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEspecialidadRepository extends JpaRepository<Especialidades,Long> {


}
