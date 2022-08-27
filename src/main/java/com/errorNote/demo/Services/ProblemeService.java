package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProblemeService {

    Probleme poserProbleme(Probleme probleme);

    List<Probleme> voirProbleme();
    Probleme modifierProbleme(Long id,Probleme probleme);
    List<Probleme> rechercherProblemeParMot(String mot);
    Probleme TrouverProblemeParTitre(String titre);



}
