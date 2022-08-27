package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.User;
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
    final private UserService userService;

    @PostMapping("/poser_probleme/{mdp}/{email}")
    public String poserProbleme(@RequestBody Probleme probleme, @PathVariable("mdp") String mdp, @PathVariable("email") String email) {
        User user = userService.findUserByEmail(email);
        if (userService.seConnecter(mdp, email)) {
            probleme.setDateProbleme(new Date());
            probleme.setUser(user);
            problemeService.poserProbleme(probleme);
            return "Vous etes bien connecter et les donnees sont enregistres";
        } else
            return "Mot de passe ou Email incorrect";
    }

    @GetMapping("/voir_probleme")
    public List<Probleme> voirProbleme() {
        return problemeService.voirProbleme();
    }

    @PutMapping("/modifier_probleme/{email}/{mdp}/{titre}")
    public String modifierProbleme(@PathVariable("email") String email, @PathVariable("mdp") String mdp, @PathVariable("titre") String titre, @RequestBody Probleme probleme) {
        User user = userService.findUserByEmail(email);
        if (userService.seConnecter(mdp, email)) {
            probleme.setDateProbleme(new Date());
            probleme.setUser(user);
            Probleme probleme1 = problemeService.TrouverProblemeParTitre(titre);
            problemeService.modifierProbleme(probleme1.getIdP(), probleme);
            return "Vous etes bien connecter et les modifications des donnees sont bien pris en compte";
        } else
            return "Mot de passe ou Email incorrect";


    }

    @GetMapping("/trouver_par_mot/{mot_cle}")
    public List<Object> trouverProblemeParMotCle(@PathVariable("mot_cle") String mot_cle) {
        return problemeService.rechercherProblemeParMot(mot_cle);
    }


}
