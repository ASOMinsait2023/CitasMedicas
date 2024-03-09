package com.misait.Controllers;

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
        return ResponseEntity.ok(iTipoCitasService.findAll());
    }

    @PostMapping("/tipoCitas")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> saveTiposCitas(@RequestBody TipoCitas tipoCitas){
        try{
            var guardar=iTipoCitasService.saveTC(tipoCitas);
            return new ResponseEntity<>(guardar,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/tipoCitas/{id}")
    ResponseEntity<?> deleteTipoCitasId(@PathVariable("id") Long idTipoCitas){
        try{
            var delete=iTipoCitasService.deleteTipoCitasById(idTipoCitas);
            return new ResponseEntity<>(delete,HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/tipoCitas/{id}")
    ResponseEntity<?> update(@RequestBody TipoCitas tipoCitas,@PathVariable("id") Long tipoCitasId){
        try{
            var tipocitas=iTipoCitasService.finfById(tipoCitasId);
            tipocitas.setTipoCita(tipoCitas.getTipoCita());
            return new ResponseEntity<>(iTipoCitasService.saveTC(tipocitas),HttpStatus.CREATED);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }




}
