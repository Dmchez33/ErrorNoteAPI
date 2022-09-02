package com.errorNote.demo.Modeles;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String nom;
    private String prenom;
    private String contact;
    private String email;
    private String password;

    @ManyToOne
    @JoinColumn(name = "idProfil")
    private Profil profil;
}
