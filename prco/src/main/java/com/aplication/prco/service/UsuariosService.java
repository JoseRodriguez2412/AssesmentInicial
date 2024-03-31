package com.aplication.prco.service;

import com.aplication.prco.repository.UsuariosRepository;
import com.aplication.prco.data.dto.UsuariosDTO;
import com.aplication.prco.data.dto.RespuestaGenerica;
import com.aplication.prco.data.Usuarios;
import com.aplication.prco.utils.Constantes;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class UsuariosService {
    //Inyección de dependencias para acceder a BD
    @Autowired
    UsuariosRepository usuarioRepository;

    //Obtener todos los usuarios registrados en la BD
    public List<UsuariosDTO> getUsuarios(){
        List<UsuariosDTO> listaUsuarios = new ArrayList();
        for(Usuarios usuario: usuarioRepository.findAll()){
            UsuariosDTO usuarioDto = new UsuariosDTO();
            usuarioDto.setId(usuario.getId());
            usuarioDto.setNombre(usuario.getNombre());
            usuarioDto.setApellidoPaterno(usuario.getApellidoPaterno());
            usuarioDto.setApellidoMaterno(usuario.getApellidoMaterno());
            usuarioDto.setEmail(usuario.getEmail());
            usuarioDto.setTelefono(usuario.getTelefono());
            usuarioDto.setRol(usuario.getRol());

            //Agregar el DTO del usuario a la lista
            //listaUsuarios.add(usuarioDto);
            listaUsuarios.add(usuarioDto);
        }
        //Retornar lista de usuarios obtenida
        return listaUsuarios;
    }

    //Guardar en BD un nuevo usuario, aplicando validaciones
    public RespuestaGenerica saveNewUsuario(@Valid UsuariosDTO dtoUser){
        //Instanciar un objeto de respuesta generica para mostrar al usuario el status del registro
        RespuestaGenerica respuesta = new RespuestaGenerica();

        //Asignar valores
        Usuarios usuario = new Usuarios();
        usuario.setNombre(dtoUser.getNombre());
        usuario.setApellidoPaterno(dtoUser.getApellidoPaterno());
        usuario.setApellidoMaterno(dtoUser.getApellidoMaterno());
        usuario.setEmail(dtoUser.getEmail());
        usuario.setPassword(dtoUser.getPassword());
        usuario.setTelefono(dtoUser.getTelefono());
        usuario.setRol(dtoUser.getRol());

        //Guardar en BD por medio del repositorio
        usuarioRepository.save(usuario);
        dtoUser.setId(usuario.getId());

        //Regresar respuesta de estatus
        respuesta.setExito(true);
        respuesta.setMensaje(Constantes.EXITO_REGISTRO_USUARIO);
        respuesta.getDatos().add(dtoUser);
        return respuesta;
    }
}
