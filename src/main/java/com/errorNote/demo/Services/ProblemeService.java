package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ProblemeService {

    Probleme poserProbleme(Probleme probleme);

    List<Probleme> voirProbleme();
    Probleme modifierProbleme(Long id,Probleme probleme);
    List<Object> rechercherProblemeParMot(String mot);
    Probleme TrouverProblemeParTitre(String titre);
    Probleme trouverProblemeParUser(User user);

    List<Object> rechercherProblemeSolutionEtCommentaireParMot(String mot);



}
