package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Commentaire;
import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Solution;
import com.errorNote.demo.Modeles.User;
import com.errorNote.demo.Services.CommentaireService;
import com.errorNote.demo.Services.ProblemeService;
import com.errorNote.demo.Services.SolutionService;
import com.errorNote.demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/commentaire")
public class CommentaireController {
    @Autowired
    final private CommentaireService commentaireService;
    final private UserService userService;
    final private ProblemeService problemeService;
    final private SolutionService solutionService;

    @PostMapping("/commenter/{mdp}/{email}")
    public String commenter(@PathVariable("email") String email, @PathVariable("mdp") String mdp, @RequestBody Commentaire commentaire){
        User user = userService.findUserByEmail(email);
        Probleme probleme = problemeService.trouverProblemeParUser(user);
        Solution solution = solutionService.trouverSolutionParProbleme(probleme);
        if(userService.seConnecter(mdp, email))
        {
            commentaire.setDateCommentaire(new Date());
            commentaire.setUser(user);
            commentaire.setSolution(solution);
            commentaireService.commenter(commentaire);
            return "COMMENTAIRE BIEN AJOUTER";
        }else
            return "LES COMMENTAIRES NE SONT PAS AJOUTER";

        /*//User user = userService.findUserByEmail(email);
        Probleme probleme = problemeService.TrouverProblemeParTitre(titre);
        Solution solution = solutionService.trouverSolutionParProbleme(probleme);
        *//*if (userService.seConnecter(mdp,email)){
            commentaire.setUser(user);-
            commentaire.setDateCommentaire(new Date());
            commentaire.setSolution(solution);
            commentaireService.commenter(commentaire);
            return "Solution bien commenter";
        }else*//*
            return "Mot de passe ou Email incorrect";*/
    }
}
