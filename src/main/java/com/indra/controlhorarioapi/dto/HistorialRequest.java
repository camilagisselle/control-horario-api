package com.indra.controlhorarioapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class HistorialRequest {

    private LocalDate fecha;
    private LocalTime entrada;
    private LocalTime inicioColacion;
    private LocalTime finColacion;
    private LocalTime salida;
}