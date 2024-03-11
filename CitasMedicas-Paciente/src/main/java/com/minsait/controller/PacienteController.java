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

@GetMapping
    public ResponseEntity<?>findAllPacientes(){
    try{
        return ResponseEntity.ok(pacienteService.findAll());
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

@GetMapping("/paciente/{idPaciente}")
    ResponseEntity<?> findById(@PathVariable("idPaciente") Long id){
    try{
        var paciente=pacienteService.findById(id);
        return ResponseEntity.ok(paciente);
    }catch (Exception e){
        return ResponseEntity.notFound().build();
    }
}

@PostMapping("/paciente")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> savePaciente(@RequestBody Paciente paciente){
    try{
        pacienteService.save(paciente);
        return new ResponseEntity<>(paciente,HttpStatus.CREATED);
    }catch (Exception e){
        return ResponseEntity.badRequest().build();
    }
}

//corregir crea uno nuevo al mismo tiempo que actualiza
@PutMapping("/paciente/{idPaciente}")
    ResponseEntity<?> update(@PathVariable("idPaciente")Long id,@RequestBody Paciente paciente){
    try{
        var pacientes=pacienteService.findById(id);
        pacientes.setName(paciente.getName());
        pacientes.setLastname(paciente.getLastname());
        pacientes.setNss(paciente.getNss());
        return new ResponseEntity<>(pacienteService.save(pacientes),HttpStatus.CREATED);
    }catch (NoSuchElementException e) {
        return ResponseEntity.notFound().build();
    }catch (Exception e){
        return ResponseEntity.badRequest().build();

    }
}

@DeleteMapping("/paciente/{idPaciente}")
    ResponseEntity<?> delete(@PathVariable("idPaciente") Long id){
    try{
        return ResponseEntity.ok(pacienteService.deleteById(id));

    }catch (NoSuchElementException e){
        return ResponseEntity.notFound().build();
    }
}






}
