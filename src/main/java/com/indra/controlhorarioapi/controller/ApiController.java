package com.indra.controlhorarioapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @GetMapping(value = "/")
    public String index() {
        log.info("acceso a index");
        return "No permitido";
    }
}
