package com.indra.controlhorarioapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

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
    private List<Usuario> usuario;
}
