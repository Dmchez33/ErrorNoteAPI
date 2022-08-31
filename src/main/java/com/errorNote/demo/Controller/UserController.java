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

    //METHODE PERMETTANT A  L'UTILISATEUR DE MODIFIER SON COMPTE ;
    @PutMapping("/modifier_compte/{email}/{mdp}/{profil}/{iduser}")
    public String modifierCompte(@PathVariable("iduser") Long idUser, @RequestBody User user, @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);// INSTATIATION D'UN USER EN FONCTION DE SON EMAIL
        Profil profil1 = profilService.trouverProfilParLibelle(profil); // RECUPERATION D'UN PROFIL EN FONCTION DE SON LIBELLE
        if(profil1.equals(user1.getProfil()))
        {
            if (userService.seConnecter(mdp, email) && (user1 != null) && (user1.getIdUser() == idUser)) {

                user.setProfil( user1.getProfil());
                userService.modifierCompte(idUser, user);
                return "COMPTE MODIFIER AVEC SUCCES";
            } else {
                return "CE COMPTE NE VOUS APPARTIENT PAS";
            }
        }else
        {
            return "VOTRE PROFIL EST INCORRECT";
        }

    }

    //METHODE PERMETTANT DE SUPPRIMER UN COMPTE UTILISATEUR
    @DeleteMapping("/supprimer_compte/{email}/{mdp}/{profil}/{idUser}")
    public String supprimerCompte(@PathVariable("idUser") String idUser,  @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);
        Profil profil1 = profilService.trouverProfilParLibelle(profil);
        if(profil.equals("Admin"))
        {
            if (userService.seConnecter(mdp, email) && (profil1 == user1.getProfil())) {

                userService.supprimerCompte(Long.valueOf(idUser));
                return "COMPTE SUPPRIMER AVEC SUCCESS";
            } else {
                return "VEUILLEZ VOUS AUTHENTIFIER POUR POUVOIR EFFECTUER CETTE ACTION";
            }
        }else
        {
            return "VOUS N'AVEZ PAS LE DROIT DE SUPPRIMER CE COMPTE";
        }

    }
    //METHODE PERMETTANT A L'ADMIN DE MODIFIER LE COMPTE D'UN UTILISATEUR DONNE
    @PutMapping("/modifier_compte_user/{email}/{mdp}/{profil}/{emails}")
    public String Compte( @RequestBody User user,  @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("emails") String emails, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);
        User user2 = userService.findUserByEmail(emails);
        Long idUser=user2.getIdUser();
        Profil profil1 = profilService.trouverProfilParLibelle(profil);
        if(profil.equals("Admin"))
        {
            if (userService.seConnecter(mdp, email) && (profil1 == user1.getProfil())) {
                user.setPrenom(user2.getPrenom());
                user.setNom(user2.getNom());
                user.setContact(user2.getContact());
                user.setEmail(user2.getEmail());
                user.setPassword(user2.getPassword());
                userService.modifierCompte(idUser,user);
                return "COMPTE MODIFIER AVEC SUCCESS";
            } else {
                return "VEUILLEZ VOUS AUTHENTIFIER POUR POUVOIR EFFECTUER CETTE ACTION";
            }
        }else
        {
            return "VOUS N'AVEZ PAS LE DROIT DE MODIFIER CE COMPTE";
        }

    }

    //METHODE PERMETTANT D'AFFICHER  LES UTILISATEURS DE NOTRE SYSTEME
    @GetMapping("/afficher")
    public List<User> afficher(){
        return userService.AfficherCompte();
    }


}
