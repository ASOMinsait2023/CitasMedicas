package com.minsait.repository;

import com.minsait.models.Especialidades;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadRepository extends JpaRepository<Especialidades,Long> {

}
