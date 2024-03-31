package com.aplication.prco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aplication.prco.data.dto.RespuestaGenerica;
import com.aplication.prco.data.dto.SesionesDTO;
import com.aplication.prco.service.SesionesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sesiones")
@Validated
public class SesionesController {
    @Autowired
    private SesionesService sesionService;

    @GetMapping("/getSesiones")
    public List<SesionesDTO> getAllSesiones(){
        return sesionService.getSesiones();
    }

    @PostMapping("/saveNewSesion")
    public ResponseEntity<RespuestaGenerica> saveNewSesion(@Valid @RequestBody SesionesDTO dtoSession){
        RespuestaGenerica respuesta = sesionService.saveNewSesion(dtoSession);
        HttpStatus status = null;
        if(respuesta.isExito()){
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        }
        else{
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta, status);
    }
}
