package com.minsait.controller;


import com.minsait.models.Citas;

import com.minsait.service.ICitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/citas")
public class CitaController {
    @Autowired
    private ICitasService citasService;

    @GetMapping("/citasHoy")
    public List<Citas> finadHoy(){
        return citasService.findStatus();
    }


    @GetMapping
    public ResponseEntity<?> findAllCitas(){
        try {
            return ResponseEntity.ok(citasService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener citas");
        }
    }

    @GetMapping("/{idCitas}")
    ResponseEntity<?> findById(@PathVariable Long idCitas){
        try{
            var cita = citasService.findById(idCitas);
            return ResponseEntity.ok(cita);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cita no encontrada");
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> save (@RequestBody Citas citas){
        try{
            citasService.save(citas);
            return new ResponseEntity<>(citas, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idCitas}")
    ResponseEntity<?> delete(@PathVariable Long idCitas) {
        try {
            return ResponseEntity.ok(citasService.deleteById(idCitas));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{idCitas}")
    ResponseEntity<?> update (@PathVariable Long idCitas, @RequestBody Citas citas) {
        try {
            var agendar = citasService.findById(idCitas);
            agendar.setDescripcion(citas.getDescripcion());
            agendar.setFechaCita(citas.getFechaCita());
            agendar.setEstatus(citas.getEstatus());
            agendar.setIdTipoCita(citas.getIdTipoCita());
            agendar.setIdPaciente(citas.getIdPaciente());
            agendar.setIdDoctor(citas.getIdDoctor());
            return new ResponseEntity<>(citasService.save(agendar), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
