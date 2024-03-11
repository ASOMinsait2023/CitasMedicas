package com.misait.controllers;

import com.misait.service.IDoctoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/doctores")
public class DoctoresController {
    @Autowired
    IDoctoresService doctoresService;

    @GetMapping
    public ResponseEntity<?> findAllDoctores(){
        return ResponseEntity.ok(doctoresService.findAll());
    }
}
