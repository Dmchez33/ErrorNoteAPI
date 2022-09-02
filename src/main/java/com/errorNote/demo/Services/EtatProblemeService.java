package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.EtatProbleme;

import java.util.List;

public interface EtatProblemeService {
    List<EtatProbleme> voirEtat();
    EtatProbleme creerEtat(EtatProbleme etatProbleme);
    EtatProbleme modifierEtat(Long idetat,EtatProbleme etatProbleme);
    EtatProbleme trouverParEtat(String etat);
}
