package com.misait.repository;

import com.misait.models.TipoCitas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoCitasRepository extends JpaRepository<TipoCitas,Long> {
}
