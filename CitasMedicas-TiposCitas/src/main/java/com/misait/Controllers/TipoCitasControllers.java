package com.misait.Controllers;

import com.misait.service.ITipoCitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tipocitas")
public class TipoCitasControllers {

    @Autowired
    ITipoCitasService iTipoCitasService;

    @GetMapping
    public ResponseEntity<?> findAllTipoCitas(){
        return ResponseEntity.ok(iTipoCitasService.findAll());
    }
}
