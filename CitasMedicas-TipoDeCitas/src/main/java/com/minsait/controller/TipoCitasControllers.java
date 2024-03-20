package com.minsait.controller;

import com.minsait.models.TipoCitas;
import com.minsait.service.ITipoCitasService;
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
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{idtipocita}")
    ResponseEntity<?> findById(@PathVariable Long idtipocita){
        try {
            var tipocita=iTipoCitasService.findById(idtipocita);
            return ResponseEntity.ok(tipocita);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> saveTipoCitas(@RequestBody TipoCitas tipoCitas){
        try{
            iTipoCitasService.save(tipoCitas);
            return new ResponseEntity<>(tipoCitas,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
@PutMapping("/{idtipocita}")
    ResponseEntity<?> update(@PathVariable Long idtipocita,@RequestBody TipoCitas tipoCitas){
        try {
            var citast=iTipoCitasService.findById(idtipocita);
            citast.setTipo_cita(tipoCitas.getTipo_cita());
            return new ResponseEntity<>(iTipoCitasService.save(citast),HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
}

@DeleteMapping("/{idtipocita}")
    ResponseEntity<?> delete(@PathVariable Long idtipocita){
        try{
            return ResponseEntity.ok(iTipoCitasService.deleteById(idtipocita));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
}



}
