package com.misait.controllers;

import com.misait.models.TipoCitas;
import com.misait.service.ITipoCitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/tipocitas")
public class TipoCitasControllers {
    @Autowired
    ITipoCitasService iTipoCitasService;

    @GetMapping
    public ResponseEntity<?> findAllTipoCitas(){
        try{
            return ResponseEntity.ok(iTipoCitasService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/tipocita/{idtipocita}")
    ResponseEntity<?> findById(@PathVariable("idtipocita") Long id){
        try {
            var tipocita=iTipoCitasService.findById(id);
            return ResponseEntity.ok(tipocita);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/tipocita")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> saveTipoCitas(@RequestBody TipoCitas tipoCitas){
        try{
            iTipoCitasService.save(tipoCitas);
            return new ResponseEntity<>(tipoCitas,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
@PutMapping("/tipocita/{id}")
    ResponseEntity<?> update(@PathVariable("id")Long id,@RequestBody TipoCitas tipoCitas){
        try {
            var citast=iTipoCitasService.findById(id);
            citast.setTipo_cita(tipoCitas.getTipo_cita());
            return new ResponseEntity<>(iTipoCitasService.save(citast),HttpStatus.CREATED);
        }catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
}

@DeleteMapping("/tipocita/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(iTipoCitasService.deleteById(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
}



}
