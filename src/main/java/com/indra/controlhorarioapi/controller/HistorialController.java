package com.indra.controlhorarioapi.controller;

import com.indra.controlhorarioapi.dto.HistorialRequest;
import com.indra.controlhorarioapi.dto.HistorialResponse;
import com.indra.controlhorarioapi.model.Historial;
import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.HistorialRepository;
import com.indra.controlhorarioapi.repository.UsuarioRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/control-horario/historial")
public class HistorialController {

    private final UsuarioRepository usuarioRepository;
    private final HistorialRepository historialRepository;

    public HistorialController(UsuarioRepository usuarioRepository,
                               HistorialRepository historialRepository) {
        this.usuarioRepository = usuarioRepository;
        this.historialRepository = historialRepository;
    }

    @GetMapping
    public List<HistorialResponse> getAllHistorial() {

        return historialRepository.findAll()
                .stream()
                .map(h -> new HistorialResponse(
                        h.getId(),
                        h.getFecha(),
                        h.getEntrada(),
                        h.getInicioColacion(),
                        h.getFinColacion(),
                        h.getSalida(),
                        h.getUsuario().getCorreo()
                ))
                .toList();
    }

    @GetMapping("/usuario/{correo}")
    public ResponseEntity<List<HistorialResponse>> obtenerHistorialPorCorreo(
            @PathVariable String correo) {

        List<HistorialResponse> historial = historialRepository
                .findByUsuarioCorreo(correo)
                .stream()
                .map(h -> new HistorialResponse(
                        h.getId(),
                        h.getFecha(),
                        h.getEntrada(),
                        h.getInicioColacion(),
                        h.getFinColacion(),
                        h.getSalida(),
                        h.getUsuario().getCorreo()
                ))
                .toList();

        return historial.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(historial);
    }

    @PostMapping("/{correo}")
    public ResponseEntity<Historial> registrarAccion(
            @PathVariable String correo,
            @RequestBody HistorialRequest request) {

        LocalDate fecha = request.getFecha();

        Historial historial = historialRepository
                .findByUsuarioCorreoAndFecha(correo, fecha)
                .orElseGet(() -> {
                    Historial nuevo = new Historial();
                    nuevo.setFecha(fecha);

                    Usuario usuario = usuarioRepository.findByCorreo(correo)
                            .orElseThrow(() ->
                                    new ResourceNotFoundException("Usuario no encontrado"));

                    nuevo.setUsuario(usuario);
                    return nuevo;
                });

        if (request.getEntrada() != null)
            historial.setEntrada(request.getEntrada());

        if (request.getInicioColacion() != null)
            historial.setInicioColacion(request.getInicioColacion());

        if (request.getFinColacion() != null)
            historial.setFinColacion(request.getFinColacion());

        if (request.getSalida() != null)
            historial.setSalida(request.getSalida());

        Historial guardado = historialRepository.save(historial);

        return ResponseEntity.ok(guardado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Historial> actualizarMarcacion(
            @PathVariable Long id,
            @RequestBody HistorialRequest historialRequest) {

        Historial historial = historialRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Historial no encontrado con id = " + id));

        if (historialRequest.getInicioColacion() != null) {
            historial.setInicioColacion(historialRequest.getInicioColacion());
        }

        if (historialRequest.getFinColacion() != null) {
            historial.setFinColacion(historialRequest.getFinColacion());
        }

        if (historialRequest.getSalida() != null) {
            historial.setSalida(historialRequest.getSalida());
        }

        Historial actualizado = historialRepository.save(historial);

        return ResponseEntity.ok(actualizado);
    }
}