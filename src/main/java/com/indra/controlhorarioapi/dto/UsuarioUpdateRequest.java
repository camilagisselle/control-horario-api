package com.indra.controlhorarioapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioUpdateRequest {

    private String nombre;
    private Integer estado;
    private Integer perfilId;
    
}
