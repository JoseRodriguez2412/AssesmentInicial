package com.aplication.prco.data.dto;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RespuestaGenerica {
    private int codigo;
    private boolean exito;
    private String mensaje;
    private List<Object> datos;

    public RespuestaGenerica(){this.datos = new ArrayList<>();}

    public RespuestaGenerica(int codigo, String mensaje, boolean exito){
        super();
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.exito = exito;
    }
}
