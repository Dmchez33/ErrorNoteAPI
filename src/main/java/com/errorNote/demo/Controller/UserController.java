package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Modeles.User;
import com.errorNote.demo.Services.ProfilService;
import com.errorNote.demo.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        if (userService.seConnecter(user.getPassword(), user.getEmail()) && (profil1 != null)) {
            return "CE COMPTE EXISTE DEJA";
        } else if (profil1 == null) {
            return "CE PROFIL N'EXISTE PAS";
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
        if (userService.seConnecter(user.getPassword(), user.getEmail()) && (profil1 != null)) {
            return "CE COMPTE EXISTE DEJA";
        } else if (profil1 == null) {
            return "CE PROFIL N'EXISTE PAS";
        } else {
            user.setProfil(profil1);
            userService.CreerCompte(user);
            return "COMPTE CREER AVEC SUCCES";
        }

    }

    //METHODE PERMETTANT A  L'UTILISATEUR DE MODIFIER SON COMPTE ;
    @PutMapping("/modifier_compte/{email}/{mdp}/{profil}/{iduser}")
    public String modifierSonCompte(@PathVariable("iduser") Long idUser, @RequestBody User user, @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);// INSTATIATION D'UN USER EN FONCTION DE SON EMAIL
        Profil profil1 = profilService.trouverProfilParLibelle(profil); // RECUPERATION D'UN PROFIL EN FONCTION DE SON LIBELLE
        if (profil1.equals(user1.getProfil())) {
            if (userService.seConnecter(mdp, email) && (user1 != null) && (user1.getIdUser() == idUser)) {

                user.setProfil(user1.getProfil());
                userService.modifierCompte(idUser, user);
                return "COMPTE MODIFIER AVEC SUCCES";
            } else {
                return "CE COMPTE NE VOUS APPARTIENT PAS";
            }
        } else {
            return "VOTRE PROFIL EST INCORRECT";
        }

    }

    //METHODE PERMETTANT DE SUPPRIMER UN COMPTE UTILISATEUR

    @DeleteMapping("/supprimer_compte/{email}/{mdp}/{profil}/{idUser}")
    public String supprimerCompte(@PathVariable("idUser") String idUser, @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);
        Profil profil1 = profilService.trouverProfilParLibelle(profil);
        if (profil.equals("Admin")) {
            if (userService.seConnecter(mdp, email) && (profil1 == user1.getProfil())) {

                userService.supprimerCompte(Long.valueOf(idUser));
                return "COMPTE SUPPRIMER AVEC SUCCESS";
            } else {
                return "VEUILLEZ VOUS AUTHENTIFIER POUR POUVOIR EFFECTUER CETTE ACTION";
            }
        } else {
            return "VOUS N'AVEZ PAS LE DROIT DE SUPPRIMER CE COMPTE";
        }

    }

    //METHODE PERMETTANT A L'ADMIN DE MODIFIER LE COMPTE D'UN UTILISATEUR DONNE
    @PutMapping("/modifier_compte_user/{email}/{mdp}/{profil}/{emails}")
    public String ModifierCompte(@RequestBody User user, @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("emails") String emails, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);// RECUPERATION DE L'ADMIN QUI VEUT MODIFIER LE COMPTE D'UN USER
        User userModifier = userService.findUserByEmail(emails);// RECUPERATION DE L'UTILISATEUR DONT ON VEUT MODIFIER LE DROIT
        Long idUser = userModifier.getIdUser(); //RECUPERATION DE L IDENTIFIANT DE L'UTILISATEUR DONT ON VEUT MODIFIER LE DROIT
        Profil profil1 = profilService.trouverProfilParLibelle(profil);// RECUPERATION DU PROFIL DE L ADMIN
        if (profil.equals("Admin")) {
            if (userService.seConnecter(mdp, email) && (profil1 == user1.getProfil())) {
                user.setPrenom(userModifier.getPrenom());
                user.setNom(userModifier.getNom());
                user.setContact(userModifier.getContact());
                user.setEmail(userModifier.getEmail());
                user.setPassword(userModifier.getPassword());

                userService.modifierCompte(idUser, user);
                return "LE DROIT DE L'UTILISATEUR EST MODIFIER AVEC SUCCESS";
            } else {
                return "VEUILLEZ VOUS AUTHENTIFIER POUR POUVOIR EFFECTUER CETTE ACTION";
            }
        } else {
            return "VOUS N'AVEZ PAS LE DROIT DE MODIFIER LE DROIT DE CE UTILISATEUR";
        }

    }

    //METHODE PERMETTANT D'AFFICHER  LES UTILISATEURS DE NOTRE SYSTEME
    @GetMapping("/afficher")
    public List<User> afficher() {
        return userService.AfficherCompte();
    }


}
