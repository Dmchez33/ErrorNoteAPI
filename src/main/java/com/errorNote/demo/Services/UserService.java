package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.User;

public interface UserService {

    User CreerCompte(User user);
    boolean seConnecter(String mdp, String email);
    User modifierCompte(Long idUser, User user);
}
