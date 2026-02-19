package com.indra.controlhorarioapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dispositivos_permitidos")
public class DispositivoPermitido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String uuid;

    @Column(nullable = false)
    private boolean activo = true;

    public DispositivoPermitido() {
    }

    public DispositivoPermitido(String uuid) {
        this.uuid = uuid;
        this.activo = true;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

