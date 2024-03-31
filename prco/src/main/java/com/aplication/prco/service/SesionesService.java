package com.aplication.prco.service;

import com.aplication.prco.repository.SesionesRepository;
import com.aplication.prco.repository.UsuariosRepository;
import com.aplication.prco.repository.RegistroAsistenciasRepository;
import com.aplication.prco.data.dto.SesionesDTO;
import com.aplication.prco.data.dto.RegistroAsistenciaDTO;
import com.aplication.prco.data.dto.RespuestaGenerica;
import com.aplication.prco.data.RegistroAsistencia;
import com.aplication.prco.data.RegistroAsistenciaId;
import com.aplication.prco.data.Sesiones;
import com.aplication.prco.data.Usuarios;
import com.aplication.prco.exceptions.EntityNotFoundException;
import com.aplication.prco.utils.Constantes;


import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.ArrayList;

@Service
public class SesionesService {
    //Inyección de dependencias para acceder a BD
    @Autowired
    SesionesRepository sesionRepository;
    UsuariosRepository usuarioRepository;
    RegistroAsistenciasRepository asistenciasRepository;

    //Obtener todos las sesiones registradas en la BD
    public List<SesionesDTO> getSesiones(){
        List<SesionesDTO> listaSesiones = new ArrayList();
        for(Sesiones sesion: sesionRepository.findAll()){
            SesionesDTO sesionDto = new SesionesDTO();
            sesionDto.setId(sesion.getId());
            sesionDto.setCodigoAcceso(sesion.getCodigoA());
            sesionDto.setLink(sesion.getLink());
            sesionDto.setAsunto(sesion.getAsunto());
            sesionDto.setFecha(sesion.getFecha());
            sesionDto.setNumParticipantes(sesion.getNumParticipantes());
            
            List<RegistroAsistenciaDTO> listaParticipantesSesion = buscarUsuariosDeSesionPorId(sesion.getListaUsuarios());
            sesionDto.setListaUsuarios(listaParticipantesSesion);
            listaSesiones.add(sesionDto);

        }
        //Retornar lista de sesiones obtenida
        return listaSesiones;
    }

    public RespuestaGenerica saveNewSesion(@Valid SesionesDTO dtoSession){
        RespuestaGenerica respuesta = new RespuestaGenerica();

        Sesiones sesion = new Sesiones();
        sesion.setCodigoA(dtoSession.getCodigoAcceso());
        sesion.setLink(dtoSession.getLink());
        sesion.setAsunto(dtoSession.getAsunto());
        //dtoSession.setFecha(new Date());
        sesion.setFecha(new Date());

        int totalParticipantes = 0;
        for(RegistroAsistenciaDTO usuarioSesion : dtoSession.getListaUsuarios()){
            Usuarios usuarioBd = usuarioRepository.findById(
                usuarioSesion.getUsuarioId()
            ).orElseThrow(
                () -> new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE)
            );
            totalParticipantes++;
        }
        sesion.setNumParticipantes(totalParticipantes);
        sesionRepository.save(sesion);

        for(RegistroAsistenciaDTO usuarioSesion : dtoSession.getListaUsuarios()){
            Usuarios usuarioBd = usuarioRepository.findById(
                usuarioSesion.getUsuarioId()
            ).orElseThrow(
                () -> new EntityNotFoundException(Constantes.USUARIO_NO_EXISTENTE)
            );
            RegistroAsistencia asistenciaSesion = new RegistroAsistencia();
            RegistroAsistenciaId idAsistencia = new RegistroAsistenciaId();
            idAsistencia.setSesionId(sesion.getId());
            idAsistencia.setUsuarioId(usuarioBd.getId());
            asistenciaSesion.setId(idAsistencia);

            asistenciaSesion.setSesionId(sesion);
            asistenciaSesion.setUsuarioId(usuarioBd);
            asistenciaSesion.setAsistencia(usuarioSesion.getAsistencia());

            asistenciasRepository.save(asistenciaSesion);
        }
        dtoSession.setId(sesion.getId());

        respuesta.setExito(true);
        respuesta.setMensaje(Constantes.EXITO_NUEVA_SESION);
        respuesta.getDatos().add(dtoSession);

        return respuesta;
    }

    private List<RegistroAsistenciaDTO> buscarUsuariosDeSesionPorId(List<RegistroAsistencia> listaUsuarios){
        List<RegistroAsistenciaDTO> listaAsistenciaDto = new ArrayList();
        for(RegistroAsistencia usuariosSesion : listaUsuarios){
            RegistroAsistenciaDTO usuario = new RegistroAsistenciaDTO();
            usuario.setUsuarioId(usuariosSesion.getUsuarioId().getId());
            usuario.setSesionId(usuariosSesion.getSesionId().getId());
            usuario.setAsistencia(usuariosSesion.getAsistencia());
            listaAsistenciaDto.add(usuario);
        }
        return listaAsistenciaDto;
    }
}
