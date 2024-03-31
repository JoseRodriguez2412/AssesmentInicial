package com.aplication.prco.data.dto;

import java.util.Date;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class SesionesDTO {
    private Integer id;
    @NotNull(message="Ingresa un código de acceso valido")
    @Positive(message="Ingresa un código de acceso valido")
    private String codigoAcceso;
    @NotNull(message="Ingresa un link de sesión valido")
    private String link;
    private String asunto;
    private Date fecha;
    private int numParticipantes;

    @Valid
    private List<RegistroAsistenciaDTO> listaUsuarios;
}
