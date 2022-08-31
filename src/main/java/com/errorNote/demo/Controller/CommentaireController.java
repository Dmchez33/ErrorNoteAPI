package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.*;
import com.errorNote.demo.Services.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/commentaire")
public class CommentaireController {
    @Autowired
    final private CommentaireService commentaireService;
    final private UserService userService;
    final private ProblemeService problemeService;
    final private SolutionService solutionService;
    final private ProfilService profilService;

    //METHODE PERMETTANT DE POSTER UN COMMENTAIRE
    @PostMapping("/commenter/{email}/{mdp}/{titre}")
    public String commenter(@PathVariable("titre") String titre, @PathVariable("email") String email, @PathVariable("mdp") String mdp, @RequestBody Commentaire commentaire) {
        //INSTATIATION DES CLASSES USER EN FONCTION DE L'UTILISATEUR, DE PROBLEME EN FONCTION DU PROBLEME ET DE LA SOLUTION EN FONCTION DU PROBLEME
        User user = userService.findUserByEmail(email);
        Probleme probleme = problemeService.TrouverProblemeParTitre(titre);
        Solution solution = solutionService.trouverSolutionParProbleme(probleme);
        // CONDITION PERMETTANT D'AUTHENTIFIER L'UTILISATEURS EN FONCTION DE SONT EMAIL ET MOT  DE PASSE
        if (userService.seConnecter(mdp, email)) {
            if (solution != null) {
                // ATTRIBUTION D'UNE DATE DE FACON AUTOMATIQUE LORS DU COMMENTE
                commentaire.setDateCommentaire(new Date());

                // ATTRIBUTION DE USER A SON COMMENTAIRE
                commentaire.setUser(user);

                // AFFECTATION DE LA SOLUTION AU COMMENTAIRE
                commentaire.setSolution(solution);

                // ENREGISTREMENT DU COMMENTAIRE
                commentaireService.commenter(commentaire);

                return "COMMENTAIRE BIEN AJOUTER";

            } else {
                return "VOUS NE POUVEZ PAS COMMENTER CE PROBLEME CAR IL N'EST PAS ENCORE RESOLU";
            }
        } else
            return "VOUS NE POUVEZ COMMENTER CE PROBLEME CAR IL N'EXISTE PAS OU BIEN IL FAU BIEN LES CARACTERE DU TITRE";

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

    @GetMapping("/affichercommentaire")
    public List<Commentaire> voirCommentaire() {
        return commentaireService.voirCommentaire();
    }

    @DeleteMapping("/supprimerCommentaire/{email}/{mdp}/{idCommentaire}")
    public String supprimerCommentaire(@PathVariable("email") String email, @PathVariable("idCommentaire") Long idCommentaire, @PathVariable("mdp") String mdp) {
        Commentaire commentaire = commentaireService.trouverParId(idCommentaire);
        User user = userService.findUserByEmail(email);
        User userCom = commentaire.getUser();
        Long idComUs=userCom.getIdUser();
        Long idUs = user.getIdUser();

        if (userService.seConnecter(mdp, email)&&idComUs==idUs) {
            commentaireService.supprimerCommentaire(commentaire.getIdCom());
            return "Commentaire supprimer avec succes";

        }else {
            return "Vous n'etes pas éligible d'effectuer cette action";
        }

    }
    @DeleteMapping("/supprimerCommentaireParAdmin/{email}/{mdp}/{profil}/{idCommentaire}")
    public String supprimerCommentaireParAdmin(@PathVariable("email") String email,@PathVariable("profil") String profil, @PathVariable("idCommentaire") Long idCommentaire, @PathVariable("mdp") String mdp) {
        Commentaire commentaire = commentaireService.trouverParId(idCommentaire);
        User user = userService.findUserByEmail(email);
        Profil profil1=profilService.trouverProfilParLibelle(profil);

        if (profil.equals("Admin")) {
            if (userService.seConnecter(mdp, email)&&profil1== user.getProfil()) {
                commentaireService.supprimerCommentaire(commentaire.getIdCom());
                return "Commentaire supprimer avec succes";

            } else {
                return "Vous n'etes pas éligible d'effectuer cette action1";
            }

        }else {
            return "Vous n'etes pas éligible d'effectuer cette action2";
        }
    }
}
