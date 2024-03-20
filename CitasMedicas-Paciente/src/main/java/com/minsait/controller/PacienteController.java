package com.minsait.controller;

import com.minsait.models.Paciente;
import com.minsait.service.IPacienteService;
import com.minsait.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {
    @Autowired
    IPacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllPacientes() {
        try {
            return ResponseEntity.ok(pacienteService.findAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idPaciente}")
    ResponseEntity<?> findById(@PathVariable Long idPaciente){
        try{
            var paciente = pacienteService.findById(idPaciente);
            return ResponseEntity.ok(paciente);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> save (@RequestBody Paciente paciente){
        try{
            pacienteService.save(paciente);
            return new ResponseEntity<>(paciente, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idPaciente}")
    ResponseEntity<?> delete(@PathVariable Long idPaciente) {
        try {
            return ResponseEntity.ok(pacienteService.deleteById(idPaciente));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idPaciente}")
    ResponseEntity<?> update(@PathVariable Long idPaciente, @RequestBody Paciente paciente) {
        try {
            var pacinte = pacienteService.findById(idPaciente);
            pacinte.setName(paciente.getName());
            pacinte.setLastname(paciente.getLastname());
            pacinte.setNss(paciente.getNss());
            return new ResponseEntity<>(pacienteService.save(pacinte), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
