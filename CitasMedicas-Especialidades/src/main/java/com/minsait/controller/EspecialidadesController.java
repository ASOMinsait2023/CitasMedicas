package com.minsait.controller;

import com.minsait.models.Especialidades;
import com.minsait.service.IEspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/especialidades")
public class EspecialidadesController {
    @Autowired
    IEspecialidadService especialidadService;


    @GetMapping
    public ResponseEntity<?> findAllEspecialidades(){
        try{
            return ResponseEntity.ok(especialidadService.findAll());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/{idEspecialidad}")
    ResponseEntity<?> findById(@PathVariable Long idEspecialidad){
        try{
            var especialidades = especialidadService.findById(idEspecialidad);
            return ResponseEntity.ok(especialidades);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
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
    @DeleteMapping("{especialidadId}")
    ResponseEntity<?> deleteEspecialidadById(@PathVariable Long especialidadId){
        try{
        var delte=especialidadService.deleteEspecialidadById(especialidadId);
        return new ResponseEntity<>(delte,HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{especialidadId}")
    ResponseEntity<?> update(@RequestBody Especialidades especialidades,@PathVariable Long especialidadId ){
        try{
            var especialida=especialidadService.findById(especialidadId);
            especialida.setNombre_especialidad(especialidades.getNombre_especialidad());
            return new ResponseEntity<>(especialidadService.save(especialida),HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();

        }
    }

}
