package com.minsait.repository;

import com.minsait.models.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICitasRepository extends JpaRepository<Citas, Long> {
    //muestra todas las fechas para el dia que estamos
    @Query(value = "SELECT  *   FROM citas  WHERE  estatus=1 and date(fechahoracita ) = DATE(NOW())",nativeQuery = true)
    List<Citas> findCitasFechas();

    //contar las fechas para el dia que estamos
    @Query(value = "SELECT  COUNT(*) FROM citas  WHERE  estatus=1 and date(fechahoracita ) = DATE(NOW())",nativeQuery = true)
    List<String> fincontar();

    //muestra las fechas de cualquier dia que queramos
    @Query(value = "SELECT  *   FROM citas  WHERE  estatus=1 and date(fechahoracita ) = DATE(?1)",nativeQuery = true)
    List<Citas> findCitasFechasEspe(String fecha);

}
