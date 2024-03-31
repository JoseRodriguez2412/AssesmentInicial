package com.aplication.prco.data;

import java.util.List;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "sesiones")
public class Sesiones {
    @Id
    @Column(name = "id_sesion", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_acceso", nullable = false, length = 12)
    private String codigoA;

    @Column(name = "link_sesion", nullable = false, length = 100)
    private String link;

    @Column(name = "asunto", nullable = true, length = 100)
    private String asunto;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "numero_participantes", nullable = false)
    private int numParticipantes;

    @OneToMany(mappedBy = "sesionId", cascade = CascadeType.ALL)
    private List<RegistroAsistencia> listaUsuarios;
}
