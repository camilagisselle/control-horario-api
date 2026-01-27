package com.indra.controlhorarioapi.controller;

import com.indra.controlhorarioapi.model.Historial;
import com.indra.controlhorarioapi.repository.HistorialRepository;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    private final UsuarioRepository usuarioRepository;
    private final HistorialRepository historialRepository;

    public HistorialController(UsuarioRepository usuarioRepository, HistorialRepository historialRepository) {
        this.usuarioRepository = usuarioRepository;
        this.historialRepository = historialRepository;
    }

    @PostMapping("/{correo}")
    public ResponseEntity<Historial> createHistorial(@PathVariable(value = "correo") String correo, @RequestBody Historial historialRequest) {

        Historial historial = usuarioRepository.findByCorreo(correo).map(usuario -> {
            historialRequest.setUsuario(usuario);
            return historialRepository.save(historialRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado, con correo = " + correo));

        return new ResponseEntity<>(historial, HttpStatus.CREATED);
    }
}
