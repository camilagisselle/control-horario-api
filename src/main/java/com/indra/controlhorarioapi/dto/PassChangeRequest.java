package com.indra.controlhorarioapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassChangeRequest {
    private String token;
    private String nuevaPassword;
}