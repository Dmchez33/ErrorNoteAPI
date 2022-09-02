package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.EtatProbleme;
import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.User;
import com.errorNote.demo.Services.EtatProblemeService;
import com.errorNote.demo.Services.ProblemeService;
import com.errorNote.demo.Services.SolutionService;
import com.errorNote.demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/probleme")
public class ProblemeController {
    @Autowired
    final private ProblemeService problemeService;
    final private SolutionService solutionService;
    final private EtatProblemeService etatProblemeService;
    final private UserService userService;

    //METHODE PERMETTANT DE POSTER UN PROBLEME
    @PostMapping("/poser_probleme/{email}/{mdp}")
    public String poserProbleme(@RequestBody Probleme probleme, @PathVariable("mdp") String mdp, @PathVariable("email") String email) {

        User user = userService.findUserByEmail(email); // INSTATIATION D'UN UTILISATEUR EN FONCTION DE SON EMAIL
        EtatProbleme etatNow = etatProblemeService.trouverParEtat("Initié");//INSTIATION DE L'ETAT DU PROBLEME EN FONCTION DE SON LIBELLE
        // CONDITION PERMETTANT DE VERIFIER L'AUTHENTITER DE L'UTILISATEUR
        if (userService.seConnecter(mdp, email)) {
            // AFFECTION DE VALEUR A CERTAINS VARIABLE DU PROBLEME COMME LA DATE, L'UTILISATEUR ET L'ETAT DU PROBLEME
            probleme.setDateProbleme(new Date());
            probleme.setUser(user);
            probleme.setEtatProbleme(etatNow);

            // CONDITION PERMETTANT DE VERIFIER SI LE PROBLEME EXISTE DEJA OU PAS
            if (problemeService.TrouverProblemeParTitre(probleme.getTitre()) == null) {
                //ENREGISTREMENT DU PROBLEME
                problemeService.poserProbleme(probleme);
                return "AUTHENTIFICATION EFFECTUER AVEC SUCCESS ET LE PROBLEME EST BIEN AJOUTER";
            } else
                return "CE PROBLEME EXISTE DEJA VEUILLEZ RECHERCHER LA SOLUTION";


        } else
            return "MOT DE PASSE OU EMAIL INCORRECT";
    }

    //METHODE PERMETTANT DE VOIR LES PROBLEMES
    @GetMapping("/voir_probleme")
    public List<Probleme> voirProbleme() {
        return problemeService.voirProbleme();
    }

    //METHODE PERMETTANT DE MODIFIER UN PROBLEME
    @PutMapping("/modifier_probleme/{email}/{mdp}/{titre}/{etat}")
    public String modifierProbleme(@PathVariable("email") String email, @PathVariable("mdp") String mdp, @PathVariable("etat") String etat, @PathVariable("titre") String titre, @RequestBody Probleme probleme) {

        User user = userService.findUserByEmail(email);// INSTATIATION D'UN UTILISATEUR PAR SON EMAIL
        EtatProbleme etatNow = etatProblemeService.trouverParEtat(etat);//INSTATIATION ETAT EN FONCTION DE SON LIBELLE

        if (userService.seConnecter(mdp, email)) {
            //ATTRIBUTION DES VALEUR A CERTAINS ATTRIBUTS DU PROBLEME COMME DATE, UTILISATEUR QUI A CREER LE PROBLEME ET L'ETATPROBLEM
            probleme.setDateProbleme(new Date());
            probleme.setUser(user);
            probleme.setEtatProbleme(etatNow);
            Probleme probleme1 = problemeService.TrouverProblemeParTitre(titre);
            // CONDITION PERMETTANT DE VERIFIER SI LE PROBLEME EST TROUVER
            if (probleme1.equals(null)) {
                return "CE PROBLEME EST INTROUVABLE";
            } else {
                problemeService.modifierProbleme(probleme1.getIdP(), probleme);
                return "AUTHENTIFICATION EFFECTUER AVEC SUCCESS ET LE PROBLEME EST BIEN MODIFIER";
            }

        } else
            return "MOT DE PASSE OU EMAIL INCORRECT";

    }

    // METHODE PERMETTANT DE TROUVER UN PROBLEME PAR MOT CLE
    @GetMapping("/trouver_par_mot/{mot_cle}")
    public List<Object> trouverProblemeParMotCle(@PathVariable("mot_cle") String mot_cle) {
        List<Object> probleme = problemeService.rechercherProblemeParMot(mot_cle);
        if (probleme == null) {
            throw new RuntimeException("CE PROBLEME N'EXISTE PAS");
        } else
            return probleme;
    }

    // METHODE PERMETTANT DE TROUVER UN PROBLEME PAR MOT CLE
    @GetMapping("/trouver_par_mot_probleme_commentaire/{mot_cle}")
    public List<Object> trouverProblemeSolutionCommentaireParMotCle(@PathVariable("mot_cle") String mot_cle) {
        List<Object> probleme = problemeService.rechercherProblemeSolutionEtCommentaireParMot(mot_cle);
        if (probleme == null) {
            throw new RuntimeException("CE PROBLEME N'EXISTE PAS");
        } else
            return probleme;
    }

}
