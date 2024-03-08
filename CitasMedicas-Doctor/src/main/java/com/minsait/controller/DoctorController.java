package com.minsait.controller;


import com.minsait.models.Doctor;
import com.minsait.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/doctores")
public class DoctorController {
    @Autowired
    IDoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> findAllDoctores(){
        return ResponseEntity.ok(doctorService.findAll());

    }

    @GetMapping("/{idDoctor}")
    ResponseEntity<?> findById(@PathVariable Long idDoctor){
        try{
            var doctor = doctorService.findById(idDoctor);
            return ResponseEntity.ok(doctor);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> save (@RequestBody Doctor doctor){
        try{
            doctorService.save(doctor);
            return new ResponseEntity<>(doctor, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idDoctor}")
    ResponseEntity<?> delete(@PathVariable Long idDoctor) {
        try {
            return ResponseEntity.ok(doctorService.deleteById(idDoctor));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idDoctor}")
    ResponseEntity<?> update(@PathVariable Long idDoctor, @RequestBody Doctor doctor) {
        try {
            var doctores = doctorService.findById(idDoctor);
            doctores.setName(doctor.getName());
            doctores.setLastname(doctor.getLastname());
            doctores.setStatus(doctor.getStatus());
            doctores.setIdespecialidad(doctor.getIdespecialidad());
            return new ResponseEntity<>(doctorService.save(doctores), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search-by-especialidad/{idDoctor}")
    ResponseEntity<?> findDoctoresByEspecialidadId(@PathVariable Long idDoctor){
        return ResponseEntity.ok(doctorService.findEspecialidadesByDoctor(idDoctor));
    }


}
