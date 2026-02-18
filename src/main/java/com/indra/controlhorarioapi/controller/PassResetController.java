package com.indra.controlhorarioapi.controller;

import com.indra.controlhorarioapi.dto.PassChangeRequest;
import com.indra.controlhorarioapi.dto.PassResetRequest;
import com.indra.controlhorarioapi.service.PassResetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/control-horario/password")
public class PassResetController {

    private final PassResetService service;

    public PassResetController(PassResetService service) {
        this.service = service;
    }

    @PostMapping("/request")
    public void requestReset(@RequestBody PassResetRequest request) {

        String token = service.crearToken(request.getCorreo());
        service.crearToken(request.getCorreo());
    }

    @PostMapping("/change")
    public void changePassword(@RequestBody PassChangeRequest request) {

        service.cambiarPassword(
                request.getToken(),
                request.getNuevaPassword()
        );
    }
}