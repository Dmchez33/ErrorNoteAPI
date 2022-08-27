package com.errorNote.demo.Modeles;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Probleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idP;
    private String titre;
    private String description;
    private String technoConcerner;
    private Date dateProbleme;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idSolution")
    private Solution solution;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
