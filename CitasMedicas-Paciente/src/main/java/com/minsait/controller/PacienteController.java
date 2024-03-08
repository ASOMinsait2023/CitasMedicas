package com.minsait.controller;

import com.minsait.service.IPacienteService;
import com.minsait.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pacientes")
public class PacienteController {
    @Autowired
    IPacienteService pacienteService;
    @GetMapping
    public ResponseEntity<?> findAllPaciente(){
        return ResponseEntity.ok(pacienteService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.findById(id));
}



}
