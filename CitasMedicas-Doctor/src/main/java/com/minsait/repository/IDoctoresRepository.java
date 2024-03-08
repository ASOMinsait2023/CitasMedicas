package com.minsait.repository;

import com.minsait.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDoctoresRepository extends JpaRepository<Doctor,Long> {
    List<Doctor> findAllByEspecialidaR(Long idEspecialida);
}
