package com.minsait.repository;

import com.minsait.models.Citas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICitasRepository extends JpaRepository<Citas, Long> {
}
