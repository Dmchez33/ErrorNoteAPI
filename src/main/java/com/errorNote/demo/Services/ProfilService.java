package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Profil;

public interface ProfilService {
    Profil ajouterProfil(Profil profil);
    Profil modifierProfil(Long id, Profil profil);

    Profil trouverProfilParLibelle(String libelle);
    void suprimerProfil(Long id);

}
