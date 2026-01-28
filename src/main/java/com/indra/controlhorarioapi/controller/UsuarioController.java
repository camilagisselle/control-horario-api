package com.indra.controlhorarioapi.controller;

import com.indra.controlhorarioapi.dto.UsuarioRequest;
import com.indra.controlhorarioapi.dto.UsuarioResponse;
import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import com.indra.controlhorarioapi.dto.UsuarioUpdateRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/control-horario/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // GET /usuarios
    @GetMapping
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET /usuarios/{correo}
    @GetMapping("/{correo}")
    public UsuarioResponse obtenerUsuario(@PathVariable String correo) {
        Usuario usuario = usuarioRepository.findById(correo)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado con correo: " + correo));

        return mapToResponse(usuario);
    }

    // POST /usuarios
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse crearUsuario(@RequestBody UsuarioRequest request) {

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(request.getPassword());
        usuario.setEstado(request.getEstado());

        Usuario guardado = usuarioRepository.save(usuario);
        return mapToResponse(guardado);
    }

    // PUT /usuarios/{correo}
    @PutMapping("/{correo}")
    public UsuarioResponse actualizarUsuario(
            @PathVariable String correo,
            @RequestBody UsuarioUpdateRequest request) {

        Usuario usuario = usuarioRepository.findById(correo)
                .orElseThrow(() ->
                        new RuntimeException("Usuario no encontrado con correo: " + correo));

      if (request.getNombre() != null) {
    usuario.setNombre(request.getNombre());
}

if (request.getEstado() != null) {
    usuario.setEstado(request.getEstado());
}

        Usuario actualizado = usuarioRepository.save(usuario);
        return mapToResponse(actualizado);
    }

    // Mapper Entidad → DTO
    private UsuarioResponse mapToResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setNombre(usuario.getNombre());
        response.setCorreo(usuario.getCorreo());
        response.setEstado(usuario.getEstado());
        response.setPerfil(usuario.getPerfil());
        return response;
    }
}