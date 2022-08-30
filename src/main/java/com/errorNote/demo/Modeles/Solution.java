package com.errorNote.demo.Modeles;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idc;
    private String descriptions;
    private String resource;
    private String tempsconsacrer;
    private String methoderecherche;
    private Date dateSolution;

    @OneToOne
    @JoinColumn(name = "idProbleme")
    private Probleme probleme;

}
