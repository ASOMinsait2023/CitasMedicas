package com.minsait.controllers;

import com.minsait.models.Especialidades;
import com.minsait.service.IEspecialidadService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/especialidades")
public class EspecialidadesController {
    @Autowired
    IEspecialidadService especialidadService;

    @GetMapping
    public ResponseEntity<?> findAllEspecialidades(){

        return ResponseEntity.ok(especialidadService.findAll());
    }

    @PostMapping("/especialidad")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> saveDepartamento(@RequestBody Especialidades especialidades){
        try{
            var guardar=especialidadService.save(especialidades);
        return new ResponseEntity<>(guardar,HttpStatus.CREATED);}
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping("/especialidad/{id}")
    ResponseEntity<?> deleteEspecialidadById(@PathVariable("id") Long especialidadId){
        try{
        var delte=especialidadService.deleteEspecialidadById(especialidadId);
        return new ResponseEntity<>(delte,HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/especialidad/{id}")
    ResponseEntity<?> update(@PathVariable("id") Long especialidadId ,@RequestBody Especialidades especialidades){
        try{
            var especialida=especialidadService.findById(especialidadId);
            especialida.setNombre_especialidad(especialidades.getNombre_especialidad());
            return new ResponseEntity<>(especialidadService.save(especialida),HttpStatus.CREATED);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }




}
