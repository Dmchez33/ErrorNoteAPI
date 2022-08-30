package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.EtatProbleme;
import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Solution;
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
@RequestMapping("/solution")
public class SolutionController {
    @Autowired
    final private SolutionService solutionService;
    final private UserService userService;
    final private ProblemeService problemeService;

    final private EtatProblemeService etatProblemeService;

    @PostMapping("/poser_solution/{email}/{mdp}/{titre}")
    public String poserSolution(@PathVariable("mdp") String mdp,@PathVariable("email") String email,@PathVariable("titre") String titre,@RequestBody Solution solution)
    {
        User user = userService.findUserByEmail(email);
        Probleme probleme = problemeService.TrouverProblemeParTitre(titre);
        if (userService.seConnecter(mdp, email)) {
            //INSTATIATION D'UN ETAT EN FONCTION DE SON LIBELLE
            if (probleme.getUser()==user){
                EtatProbleme etatNow=  etatProblemeService.trouverParEtat("Résolu");
                probleme.setEtatProbleme(etatNow);//DEFINITION DE LA VALEUR DE L'ETAT DU PROBLEME APRES LA RESOLUTION
                solution.setDateSolution(new Date());// DEFINITION DE LA DATE DU RESOLUTION DU PROBLEME
                solution.setProbleme(probleme);// DEFINION DU PROBLEME DE LA SOLUTION
                // AJOUT DE LA SOLUTION
                solutionService.mettreSolution(solution);
                return "SOLUTION AJOUTER AVEC SUCCESS";
            }else
                return "VOUS N'ÊTES PAS ELIGIBLE A RESOUDRE UN PROBLEME";
        }
        else
            return "MOT DE PASSE OU EMAIL INCORRECT";
    }

    //METHODE PERMETTANT DE 
    @GetMapping("/voir_solution_par_probleme/{titreProbleme}")
    public Solution voirSolution(@PathVariable("titreProbleme") String titreProbleme)
    {
        Probleme probleme = problemeService.TrouverProblemeParTitre(titreProbleme);
        if (probleme.equals(null)){
            return null;
        }else
        return solutionService.trouverSolutionParProbleme(probleme);
    }

    @PutMapping("/modifier_solution/{idSolution}")
    public Solution modifierSolution(Long idSolution, Solution solution)
    {
        return solutionService.modifierSolution(idSolution,solution);
    }
}
