package com.aplication.prco.data.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class RegistroAsistenciaDTO {
    @NotNull(message="Debe ingresar un ID de sesion valido")
    @Positive(message="Debe ingresar un ID de sesion valido")
    private int sesionId;
    @NotNull(message="Debe ingresar un ID de usuario valido")
    @Positive(message="Debe ingresar un ID de usuario valido")
    private int usuarioId;
    @NotNull(message="Debe CONFIRMAR o DENEGAR su asistencia")
    private String asistencia;
}
