package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.EtatProbleme;
import com.errorNote.demo.Services.EtatProblemeService;
import com.errorNote.demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etat")
public class EtatProblemeController {
    @Autowired
    final private EtatProblemeService etatProblemeService;
    final private UserService userService;

    // DEFINITION DE L'ETAT DU PROBLEME
    @PostMapping("/ajouter_etat/{email}/{mdp}")
    public String ajouterEtat(@PathVariable("email") String email, @PathVariable("mdp") String mdp, @RequestBody EtatProbleme etatProbleme) {
        // CONDITION PERMETTANT DE VERIFIR L'AUTHENTITE DE L'UTILISATEUR
        if (userService.seConnecter(mdp, email)) {
            etatProblemeService.creerEtat(etatProbleme);
            return "ETAT AJOUTER AVEC SUCCESS";
        } else {
            return "ERREURS AU NIVEAU DES AJOUTS DE L'ETAT";
        }

    }

    // METHODE PERMETTANT PERMETTANT DE MODIFIER L'ETAT DU PROBLEME
    @PutMapping("/modifier_etat/{email}/{mdp}/{idEtat}")
    public EtatProbleme modifierEtat(Long idetat, EtatProbleme etatProbleme) {
        return etatProblemeService.modifierEtat(idetat, etatProbleme);
    }

    // METHODE PERMETTANT DE VOIR LES DIFFIERENT ETAT
    @GetMapping("/voirEtat")
    public List<EtatProbleme> voirEtat() {
        return etatProblemeService.voirEtat();
    }
}
