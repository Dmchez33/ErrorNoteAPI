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
    public String CreerCompte(@RequestBody User user, @PathVariable("profil") String profil){

        Profil profil1 = profilService.trouverProfilParLibelle(profil);
        if (userService.seConnecter(user.getPassword(), user.getEmail())) {
            return "CE COMPTE EXISTE DEJA";
        }
        else if (profil1 == null)
        {

            return "CE PROFIL N'EXISTE PAS";

        }else
        {
            user.setProfil(profil1);
            userService.CreerCompte(user);
            return "COMPTE CREER AVEC SUCCES";
        }

    }

    @PutMapping("/modifier_compte/{iduser}")
    public User modifierCompte(Long idUser, User user)
    {
        return userService.modifierCompte(idUser, user);
    }
}
