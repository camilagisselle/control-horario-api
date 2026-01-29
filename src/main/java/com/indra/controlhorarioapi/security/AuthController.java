package com.indra.controlhorarioapi.security;
import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import com.indra.controlhorarioapi.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthController(UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Usuario loginRequest) {
        Usuario usuario = usuarioRepository.findByCorreo(loginRequest.getCorreo()).orElse(null);
        if (usuario != null && usuario.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(jwtService.generarToken(usuario.getCorreo()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
    }
}