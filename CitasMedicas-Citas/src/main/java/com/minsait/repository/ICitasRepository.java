package com.minsait.repository;

import com.minsait.models.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICitasRepository extends JpaRepository<Citas, Long> {
    @Query(value = "SELECT  *   FROM citas  WHERE  estatus=1 and date(fechahoracita ) = DATE(NOW())",nativeQuery = true)
    List<Citas> findCitasFechas();

    @Query(value = "SELECT  COUNT(*) FROM citas  WHERE  estatus=1 and date(fechahoracita ) = DATE(NOW())",nativeQuery = true)
    List<String> fincontar();
}
