package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Services.ProblemeService;
import com.errorNote.demo.Services.SolutionService;
import com.errorNote.demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("probleme")
public class ProblemeController {
    @Autowired
    final private ProblemeService problemeService;
    final private SolutionService solutionService;
    final private UserService userService;

    @PostMapping("/poser_probleme/{mdp}/{email}")
    public String poserProbleme(@RequestBody Probleme probleme, String mdp, String email)
    {
        if (userService.seConnecter(mdp, email))
        {
            problemeService.poserProbleme(probleme);
            return "Vous etes bien connecter et les donnees sont enregistres";
        }
        else
            return "Mot de passe ou Email incorrect";
    }
    @GetMapping("/voir_probleme")
    public List<Probleme> voirProbleme(){
        return problemeService.voirProbleme();
    }

    @PutMapping("/modifier_probleme/{id}")
    public Probleme modifierProbleme(Long id, @RequestBody Probleme probleme){
        return problemeService.modifierProbleme(id, probleme);
    }


}
