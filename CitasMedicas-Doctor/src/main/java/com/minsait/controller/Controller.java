package com.minsait.controller;

import com.minsait.models.Doctor;
import com.minsait.service.DoctorService;
import com.minsait.service.IDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/doctores")
public class Controller {
    //private final DoctorService doctorService;
    @Autowired
    IDoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> findAllDoctores(){
        return ResponseEntity.ok(doctorService.buscarTodos());

    }
    @GetMapping("/doctor/{idDoctor}")
    ResponseEntity<?> findById(@PathVariable Long idDoctor){
        try{
            var doctor = doctorService.findById(idDoctor);
            return ResponseEntity.ok(doctor);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/doctor")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> save (@RequestBody Doctor doctor){
        try{
            doctorService.save(doctor);
            return new ResponseEntity<>(doctor, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/doctor/{idDoctor}")
    ResponseEntity<?> delete(@PathVariable Long idDoctor) {
        try {
            return ResponseEntity.ok(doctorService.deleteById(idDoctor));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/doctor/{idDoctor}")
    ResponseEntity<?> update(@PathVariable Long idDoctor, @RequestBody Doctor doctor) {
        try {
            var doctores = doctorService.findById(idDoctor);
            doctores.setNombre(doctor.getNombre());
            doctores.setApellidos(doctor.getApellidos());
            doctores.setEstatus(doctor.getEstatus());
            doctores.setIdespecialidad(doctor.getIdespecialidad());
            return new ResponseEntity<>(doctorService.save(doctores), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search-by-especialidad/{idEspecialidad}")
    public ResponseEntity<?> findByEspecialidad(@PathVariable("idEspecialidad") Long idEspecialidad)
    {
        var responce=doctorService.findByIdEspecialidaS(idEspecialidad);
        if (responce.isEmpty()){
            throw new NoSuchElementException("Valor no presente");
        }
        return ResponseEntity.ok(responce);
    }


}
