package com.indra.controlhorarioapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Getter @Setter
public class Usuario {

    @Column
    private String nombre;

    @Id
    @Column
    private String correo;

    @Column
    private String password;

    @Column
    private String estado;

    @OneToMany(mappedBy="usuario")
    private List<Historial> historial;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "perfil_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Perfil perfil;

}
