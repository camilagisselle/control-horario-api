package com.indra.controlhorarioapi.repository;

import com.indra.controlhorarioapi.model.Historial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {

    List<Historial> findByUsuarioCorreo(String correo);
}
