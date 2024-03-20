package com.minsait.controller;


import com.minsait.models.Citas;
import com.minsait.repository.ICitasRepository;
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
    @GetMapping("/citasHoyEspe/{fecha}")
    public List<Citas> findAllCitasHoyEspe(@PathVariable String fecha){
        return citasService.findCitasFechasEspe(fecha);
    }

    @GetMapping("/contarCitasHoy")
    public List<String> contar(){
        return citasService.fincontar() ;
    }

    @GetMapping("/citasHoy")
    public ResponseEntity<?> findAllCitasHoy(){
        try {
            return ResponseEntity.ok(citasService.findCitasFechas());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping
    public ResponseEntity<?> findAllEspecialidades(){
        try {
            return ResponseEntity.ok(citasService.findAll());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
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
            agendar.setIdTipoCita(citas.getIdTipoCita());
            agendar.setIdPaciente(citas.getIdPaciente());
            agendar.setIdDoctor(citas.getIdDoctor());
            agendar.setFechaCita(citas.getFechaCita());
            agendar.setStatus(citas.getStatus());
            return new ResponseEntity<>(citasService.save(agendar), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
