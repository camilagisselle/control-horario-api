package com.indra.controlhorarioapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class HistorialResponse {

    private Long id;
    private LocalDate fecha;
    private LocalTime entrada;
    private LocalTime inicioColacion;
    private LocalTime finColacion;
    private LocalTime salida;
    private String correoUsuario;

    public HistorialResponse(
            Long id,
            LocalDate fecha,
            LocalTime entrada,
            LocalTime inicioColacion,
            LocalTime finColacion,
            LocalTime salida,
            String correoUsuario
    ) {
        this.id = id;
        this.fecha = fecha;
        this.entrada = entrada;
        this.inicioColacion = inicioColacion;
        this.finColacion = finColacion;
        this.salida = salida;
        this.correoUsuario = correoUsuario;
    }
}