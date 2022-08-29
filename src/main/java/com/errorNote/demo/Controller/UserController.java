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

    @PostMapping("/creer_compte/{profil}")
    public String CreerCompte(@RequestBody User user, @PathVariable("profil") String profil) {

        Profil profil1 = profilService.trouverProfilParLibelle(profil);
        if (userService.seConnecter(user.getPassword(), user.getEmail())) {
            return "CE COMPTE EXISTE DEJA";
        } else if (profil1 == null) {

            return "CE PROFIL N'EXISTE PAS";

        } else {
            user.setProfil(profil1);
            userService.CreerCompte(user);
            return "COMPTE CREER AVEC SUCCES";
        }

    }

    @PutMapping("/modifier_compte/{email}/{mdp}/{profil}/{iduser}")
    public String modifierCompte(@PathVariable("iduser") Long idUser, @RequestBody User user, @PathVariable("profil") String profil, @PathVariable("email") String email, @PathVariable("mdp") String mdp) {
        User user1 = userService.findUserByEmail(email);
        Profil profil1 = profilService.trouverProfilParLibelle(profil);
        if(profil.equals("Admin"))
        {
            if (userService.seConnecter(mdp, email) && (profil1 == user1.getProfil())) {
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
