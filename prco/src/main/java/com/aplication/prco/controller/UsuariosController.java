package com.aplication.prco.controller;

import com.aplication.prco.service.UsuariosService;
import com.aplication.prco.data.dto.RespuestaGenerica;
import com.aplication.prco.data.dto.UsuariosDTO;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuariosController {
    @Autowired
    private UsuariosService usuarioService;

    @GetMapping("/getUsuarios")
    public List<UsuariosDTO> getAllUsuarios(){
        return usuarioService.getUsuarios();
    }

    @PostMapping("/saveNewUsuario")
    public ResponseEntity<RespuestaGenerica> saveNewUsuario(@Valid @RequestBody UsuariosDTO usuarioDto){
        RespuestaGenerica respuesta = usuarioService.saveNewUsuario(usuarioDto);
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
