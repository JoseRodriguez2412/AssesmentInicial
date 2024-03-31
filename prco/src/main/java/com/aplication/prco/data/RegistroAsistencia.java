package com.aplication.prco.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "registro_asistencia")
public class RegistroAsistencia {
    //Definir la clave primaria compuesta
    @EmbeddedId
    private RegistroAsistenciaId id;

    @Column(name = "asistencia", nullable = false)
    private String asistencia;

    @MapsId("usuarioId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuarioId;

    @MapsId("sesionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_sesion", nullable = false)
    private Sesiones sesionId;
}
