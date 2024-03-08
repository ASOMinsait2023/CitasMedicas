package com.minsait.client;

import com.minsait.dtos.DoctorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-doctor",url = "http://localhost:8083/api/v1/doctores")
public interface IDoctorClient {
    @GetMapping("/search-by-especialidad/{idEspecialidad}")
    List<DoctorDTO> findByEspecialidad(@PathVariable("idEspecialidad") Long idEspecialidad);

}