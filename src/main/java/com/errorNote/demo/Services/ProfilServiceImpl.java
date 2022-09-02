package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Repository.ProfilRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfilServiceImpl implements ProfilService {
    @Autowired
    final private ProfilRepository profilRepository;

    @Override
    public Profil ajouterProfil(Profil profil) {
        return profilRepository.save(profil);
    }

    @Override
    public Profil modifierProfil(Long id, Profil profil) {
        return profilRepository.findById(id).map(profil1 -> {
            profil1.setLibelle(profil.getLibelle());
            return profilRepository.save(profil);
        }).orElseThrow(() -> new RuntimeException("ERREUR AU NIVEAU DU MODIFICATION DU PROBLEME"));
    }

    @Override
    public Profil trouverProfilParLibelle(String libelle) {
        return profilRepository.findByLibelle(libelle);
    }

    @Override
    public void suprimerProfil(Long id) {
        profilRepository.deleteById(id);
    }
}
