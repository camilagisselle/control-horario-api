package com.indra.controlhorarioapi.controller;

import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import com.indra.controlhorarioapi.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/control-horario")
public class AuthController {

    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {

        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo()).orElse(null);
        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())) {
            Map<String, Object> userData = new HashMap<>();
            userData.put("correo", usuario.getCorreo());
            userData.put("nombre", usuario.getNombre());
            userData.put("estado", usuario.getEstado());

            String role = "admin".equals(loginRequest.getCorreo()) ? "ROLE_ADMIN" : "ROLE_USER";

            String token = JwtUtil.generateToken(usuario.getCorreo(), role, userData);

            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok().body(response);

        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

}