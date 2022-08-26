package com.errorNote.demo.Modeles;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EtatProbleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEp;

}
