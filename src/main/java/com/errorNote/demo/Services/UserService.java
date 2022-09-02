package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Modeles.User;

import java.util.List;

public interface UserService {

    //MEHTODE PERMETTANT DE CREER UN COMPTE UTILISATEUR ET ADMINISTRACTEUR
    User CreerCompte(User user);
    //METHODE PERMETTANT A L'UTILISATEUR DE S'AUTHENTIFIER AVANT D'EFFECTUER UNE ACTION DANS LE SYSTEME
    boolean seConnecter(String mdp, String email);
    //METHODE PERMETTANT LA MODIFICATION DE L'UTILISATEUR DANS LE SYSTEME
    User modifierCompte(Long idUser, User user);
    //METHODE PERMETTANT LA SUPPRESSION DE L'UTILISATEUR DANS LE SYSTEME EN FONCTION DE SON ID
    String supprimerCompte(Long idUser);
    //METHODE PERMETTANT DE TROUVER UN UTILISATEUR PAR SON EMAIL
    User findUserByEmail(String email);
    //METHODE PERMETTANT DE TROUVER UN UTILISATEUR PAR SON PROFIL
    User trouverUserByProfil(Profil profil);
    //METHODE PERMETTANT D'AFFICHER LES DIFFERENT COMPTE DE DANS LE SYSTEME
    List<User> AfficherCompte();
}
