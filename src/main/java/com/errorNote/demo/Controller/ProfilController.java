package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Services.ProfilService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/profil")
public class ProfilController {
    @Autowired
    final ProfilService profilService;

    //METHODE PERMETTANT D'AJOUTER UN PROFILL
    @PostMapping("/ajouter_profil")
    public Profil ajouterProfil(@RequestBody Profil profil){
        return profilService.ajouterProfil(profil);
    }

    //METHODE PERMETTANT DE SUPPRIMER UN PROFIL
    @DeleteMapping("/supprimer_profil")
    public void supprimerProfil(@PathVariable Long id){
        profilService.suprimerProfil(id);
    }
}
