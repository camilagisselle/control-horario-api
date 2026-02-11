package com.indra.controlhorarioapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistorialResponse {

    private String fecha;
    private String entrada;
    private String salida;
    private String correoUsuario;

    public HistorialResponse(
            String fecha,
            String entrada,
            String salida,
            String correoUsuario
    ) {
        this.fecha = fecha;
        this.entrada = entrada;
        this.salida = salida;
        this.correoUsuario = correoUsuario;
    }
}