package com.indra.controlhorarioapi.repository;

import com.indra.controlhorarioapi.model.DispositivoPermitido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DispositivoRepository 
        extends JpaRepository<DispositivoPermitido, Long> {

    Optional<DispositivoPermitido> findByUuidAndActivoTrue(String uuid);
}
