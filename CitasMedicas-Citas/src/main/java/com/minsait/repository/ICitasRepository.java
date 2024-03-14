package com.minsait.repository;

import com.minsait.models.Citas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICitasRepository extends JpaRepository<Citas, Long> {
@Query(value = "select *  from citas  where  estatus=1 and date(fechahoracita ) = DATE(NOW())",nativeQuery = true)
List<Citas> findStatus();




}

