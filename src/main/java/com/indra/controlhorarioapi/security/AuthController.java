package com.indra.controlhorarioapi.security;
import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import com.indra.controlhorarioapi.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/control-horario/login")
public class AuthController {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthController(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Usuario loginRequest) {
        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo()).orElse(null);
        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())) {
            Map<String, Object> userData = new HashMap<>();
            userData.put("correo", usuario.getCorreo());
            userData.put("nombre", usuario.getNombre());
            userData.put("estado", usuario.getEstado());
            //userData.put("estado", usuario.getPerfil());

            String token = jwtService.generarToken(userData);
            /*
            return ResponseEntity.ok(
                    java.util.Map.of("token", token)
            );
            */
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o clave inválidos");
        }
    }
}