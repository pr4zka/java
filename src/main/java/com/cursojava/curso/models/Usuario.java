package com.cursojava.curso.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "usuario")
@ToString @EqualsAndHashCode
public class Usuario {

    @Getter
    @Setter
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Getter
    @Setter
    @Column(name = "nombre")
    private String name;
    @Getter
    @Setter
    @Column(name = "apellido")
    private String lastname;

    @Getter
    @Setter
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "telefono")
    private String telefono;

    @Getter
    @Setter
    @Column(name = "password")
    private String password;

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
