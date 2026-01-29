package com.indra.controlhorarioapi.controller;

import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import com.indra.controlhorarioapi.security.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public UsuarioController(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {

        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo())
                .orElse(null);

        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())) {

            Map<String, Object> userData = new HashMap<>();
            userData.put("correo", usuario.getCorreo());
            userData.put("nombre", usuario.getNombre());
            userData.put("estado", usuario.getEstado());

            String token = jwtService.generarToken(userData);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);

        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o clave inválidos");
        }
    }
}
