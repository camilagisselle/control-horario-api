package com.indra.controlhorarioapi.controller;

import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return this.usuarioRepository.findAll();
    }

    @GetMapping("/{correo}")
    public Usuario getUsuarioById(@PathVariable String correo) {
        return usuarioRepository.findByCorreo(correo).orElseThrow(() -> new RuntimeException("Usuario no encontrado, con correo: " + correo));
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

}