package com.indra.controlhorarioapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistorialRequest {

    private String fecha;
    private String entrada;
    private String inicioColacion;
    private String finColacion;
    private String salida;
}