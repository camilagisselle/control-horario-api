package com.indra.controlhorarioapi.service;

import com.indra.controlhorarioapi.model.EmailDetails;
import com.indra.controlhorarioapi.model.PassResetToken;
import com.indra.controlhorarioapi.model.Usuario;
import com.indra.controlhorarioapi.repository.PassResetTokenRepository;
import com.indra.controlhorarioapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PassResetService {
    private final PassResetTokenRepository tokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmailService emailService;

    public PassResetService(PassResetTokenRepository tokenRepository,
                            UsuarioRepository usuarioRepository, EmailService emailService) {
        this.tokenRepository = tokenRepository;
        this.usuarioRepository = usuarioRepository;
        this.emailService = emailService;
    }

    public String crearToken(String correo) {

        Usuario usuario = usuarioRepository.findById(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        String token = UUID.randomUUID().toString();

        PassResetToken entity = new PassResetToken();
        entity.setCorreo(correo);
        entity.setToken(token);
        entity.setEstado(0);
        entity.setExpiracion(LocalDateTime.now().plusHours(1));

        tokenRepository.save(entity);

        String link = "http://localhost:5173/recupcontrasena";
        String mensaje = "Estimado " + usuario.getNombre() + ",\n\n" +
                "Este es su código de verificación para restablecer su contraseña:\n" +
                token + "\n" +
                "favor ingresar al siguiente link para completar el proceso\n" +
                link + "\n\n" +
                "Saludos";

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(correo);
        emailDetails.setSubject("Restablece tu contraseña");
        emailDetails.setMsgBody(mensaje);

        emailService.sendSimpleMail(emailDetails);

        return token;
    }

    public void cambiarPassword(String token, String nuevaPassword) {

        PassResetToken entity = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (entity.getEstado() == 1) {
            throw new RuntimeException("Token ya usado");
        }

        if (entity.getExpiracion().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        Usuario usuario = usuarioRepository.findById(entity.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        usuario.setPassword(nuevaPassword);
        usuarioRepository.save(usuario);

        entity.setEstado(1);
        tokenRepository.save(entity);
    }
}