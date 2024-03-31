package com.aplication.prco.data.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
//Anotaciones para generar los métodos Getter y Setter de cada atributo
@Getter
@Setter
//Anotación para hacer validaciones en los atributos
@Validated
public class UsuariosDTO {
    private Integer id;
    @NotNull(message="Ingresa un nombre")
    @NotBlank(message="Ingresa un nombre valido")
    private String nombre;
    @NotNull(message="Ingresa un apellido")
    @NotBlank(message="Ingresa un apellido valido")
    private String apellidoPaterno;
    @NotNull(message="Ingresa un apellido")
    @NotBlank(message="Ingresa un apellido valido")
    private String apellidoMaterno;
    @NotNull(message="Ingresa un email")
    @Email(message="Ingresa un email valido")
    private String email;
    @NotNull(message="Ingrese una contraseña")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", 
        message = "La contraseña no cumple con los requisitos minimos de seguridad"
    )
    private String password;
    @NotNull(message="Ingresa un numero de telefono")
    @Pattern(regexp = "[0-9]{10}", message="El numero de telefono debe contener 10 digitos numericos")
    private String telefono;
    @NotNull(message="Ingresa un rol para el usuario")
    @NotBlank(message="Ingresa un rol para el usuario")
    private String rol;
}
