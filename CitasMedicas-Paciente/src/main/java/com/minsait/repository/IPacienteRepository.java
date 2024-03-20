package com.minsait.repository;

import com.minsait.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

}
