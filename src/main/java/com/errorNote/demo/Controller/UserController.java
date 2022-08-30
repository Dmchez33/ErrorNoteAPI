package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Modeles.User;
import com.errorNote.demo.Services.ProfilService;
import com.errorNote.demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    final private UserService userService;

    final private ProfilService profilService;

    // METHODE PERMETTANT DE CREER UN COMPTE UTILISATEUR DE TYPE USER
    @PostMapping("/creer_compte_User")
    public String CreerCompte(@RequestBody User user) {

        Profil profil1 = profilService.trouverProfilParLibelle("User");
        User user1 = userService.trouverUserByProfil(profil1);
        if (userService.seConnecter(user.getPassword(), user.getEmail()) && (user1 != null)) {
            return "CE COMPTE EXISTE DEJA";
        } else {
            user.setProfil(profil1);
            userService.CreerCompte(user);
            return "COMPTE CREER AVEC SUCCES";
        }

    }

    //METHODE PERMETTANT DE CREER UN COMPTE ADMIN
    @PostMapping("/creer_compte_admin")
    public String CreerCompteAdmin(@RequestBody User user) {

        Profil profil1 = profilService.trouverProfilParLibelle("Admin");
        if (userService.seConnecter(user.getPassword(), user.getEmail()) && (user.getProfil().getLibelle() == profil1.getLibelle())) {
            return "CE COMPTE EXISTE DEJA";
        } else{
            user.setProfil(profil1);
            userService.CreerCompte(user);
            return "COMPTE CREER AVEC SUCCES";
        }

    }

    //METHODE PERMETTANT DE MODIFIER LE COMPTE DE L'UTILISATEUR;
    @PutMapping("/modifier_compte/{email}/{mdp}/{profil}/{iduser}")
    public String modifierCompte(@PathVariable("iduser") Long idUser, @RequestBody User user, @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);// INSTATIATION D'UN USER EN FONCTION DE SON EMAIL
        Profil profil1 = profilService.trouverProfilParLibelle(profil); // RECUPERATION D'UN PROFIL EN FONCTION DE SON LIBELLE
        if(profil.equals("Admin"))
        {
            if (userService.seConnecter(mdp, email) && (user1 != null)) {
                user.setProfil( user1.getProfil());
                userService.modifierCompte(idUser, user);
                return "Modification effectuer avec succes";
            } else {
                return "Vous n'etes pas eligible à effectuer cette action2";
            }
        }else
        {
            return "Vous n'etes pas eligible à effectuer cette action1";
        }

    }

    //METHODE PERMETTANT DE SUPPRIMER UN COMPTE UTILISATEUR
    @DeleteMapping("/supprimer_compte/{email}/{mdp}/{profil}/{iduser}")
    public String supprimerCompte(@PathVariable("iduser") Long idUser,  @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);
        Profil profil1 = profilService.trouverProfilParLibelle(profil);
        if(profil.equals("Admin"))
        {
            if (userService.seConnecter(mdp, email) && (profil1 == user1.getProfil())) {

                userService.supprimerCompte(idUser);
                return "Suppression effectuer avec succes";
            } else {
                return "Vous n'etes pas eligible à effectuer cette action2";
            }
        }else
        {
            return "Vous n'etes pas eligible à effectuer cette action1";
        }

    }
}
