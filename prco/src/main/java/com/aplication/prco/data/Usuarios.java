package com.aplication.prco.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "usuarios")
public class Usuarios {
    @Id
    @Column(name = "id_usuarios", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 45)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 45)
    private String apellidoMaterno;

    @Column(name = "email", nullable = false, unique = true, length = 45)
    private String email;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "telefono", nullable = false, unique = true, length = 10)
    private String telefono;

    @Column(name = "rol", nullable = false, length = 45)
    private String rol;
}
