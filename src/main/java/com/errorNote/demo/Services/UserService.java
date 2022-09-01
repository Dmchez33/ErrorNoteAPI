package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Modeles.User;

import java.util.List;

public interface UserService {

    User CreerCompte(User user);
    boolean seConnecter(String mdp, String email);
    User modifierCompte(Long idUser, User user);


    String supprimerCompte(Long idUser);
    User findUserByEmail(String email);
    User trouverUserByProfil(Profil profil);
    List<User> AfficherCompte();
}
