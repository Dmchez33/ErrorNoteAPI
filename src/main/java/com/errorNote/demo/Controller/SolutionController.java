package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Solution;
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
@RequestMapping("/solution")
public class SolutionController {
    @Autowired
    final private SolutionService solutionService;
    final private UserService userService;
    final private ProblemeService problemeService;

    @PostMapping("/poser_solution/{mdp}/{email}")
    public String poserSolution(@PathVariable("mdp") String mdp,@PathVariable("email") String email,@RequestBody Solution solution)
    {
        User user = userService.findUserByEmail(email);
        Probleme probleme = problemeService.trouverProblemeParUser(user);
        if (userService.seConnecter(mdp, email)) {
            solution.setDateSolution(new Date());
            solution.setProbleme(probleme);
            solutionService.mettreSolution(solution);
            return "Solution Ajouter avec Success";
        }
        else
            return "Erreur au niveau de l'ajout";
    }

    @GetMapping("/voir_solution")
    public List<Solution> voirSolution()
    {
        return solutionService.voirSolution();
    }

    @PutMapping("/modifier_solution/{idSolution}")
    public Solution modifierSolution(Long idSolution, Solution solution)
    {
        return solutionService.modifierSolution(idSolution,solution);
    }
}
