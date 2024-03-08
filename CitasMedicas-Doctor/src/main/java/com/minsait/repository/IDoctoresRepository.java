package com.minsait.repository;

import com.minsait.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctoresRepository  extends JpaRepository<Doctor, Long> {
}
