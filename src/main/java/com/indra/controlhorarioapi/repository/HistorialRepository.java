package com.indra.controlhorarioapi.repository;

import com.indra.controlhorarioapi.model.Historial;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    List<Historial> findByUsuarioCorreo(String correo);
    List<Historial> findAllByFechaBetween(LocalDate fechaStart, LocalDate fechaEnd);
    Optional<Historial> findByUsuarioCorreoAndFecha(String correo, LocalDate fecha);
}