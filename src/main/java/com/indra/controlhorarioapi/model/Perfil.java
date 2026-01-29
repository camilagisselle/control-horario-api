package com.indra.controlhorarioapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
public class Perfil {

    @Id
    @Column
    private long perfil_id;

    @Column
    private String perfil_nombre;

    @OneToMany(mappedBy="perfil")
    @JsonIgnore
    private List<Usuario> usuario;
}
