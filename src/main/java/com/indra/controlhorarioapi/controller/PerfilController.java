package com.indra.controlhorarioapi.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.indra.controlhorarioapi.model.Perfil;
import com.indra.controlhorarioapi.repository.PerfilRepository;

@RestController
@RequestMapping("/v1/control-horario/perfil")
public class PerfilController {

    private final PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public List<Perfil> getAllPerfiles() {
        return this.perfilRepository.findAll();
    }

}