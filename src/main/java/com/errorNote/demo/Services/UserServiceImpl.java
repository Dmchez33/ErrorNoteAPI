package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Modeles.User;
import com.errorNote.demo.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    final private UserRepository userRepository;

    @Override
    public User CreerCompte(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean seConnecter(String mdp, String email) {
        /*if ((userRepository.findByEmail(email) == null) || (userRepository.findByPassword(mdp) == null))
            return false;
        else
            return true;*/
        if (userRepository.findByEmailAndPassword(email, mdp) == null)
            return false;
        else
            return true;
    }

    @Override
    public User modifierCompte(Long idUser, User user) {
        return userRepository.findById(idUser).map(user1 -> {
            user1.setContact(user.getContact());
            user1.setEmail(user.getEmail());
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setPassword(user.getPassword());
            user1.setProfil(user.getProfil());
            return userRepository.save(user1);
        }).orElseThrow(() -> new RuntimeException("ERREUR AU NIVEAU DU MODIFICATION DE L UTILISATEUR"));
    }

    @Override
    public User modifierComptU(Long idUser, User user) {
        return userRepository.findById(idUser).map(user1 -> {
            user1.setContact(user.getContact());
            user1.setEmail(user.getEmail());
            user1.setNom(user.getNom());
            user1.setPrenom(user.getPrenom());
            user1.setPassword(user.getPassword());
            user1.setProfil(user.getProfil());
            return userRepository.save(user1);
        }).orElseThrow(() -> new RuntimeException("ERREUR AU NIVEAU DU MODIFICATION DE L UTILISATEUR"));
    }

    @Override
    public String supprimerCompte(Long idUser) {
        userRepository.deleteById(idUser);
        return "Utilisateur supprimer avec succes";
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User trouverUserByProfil(Profil profil) {
        return userRepository.findByProfil(profil);
    }

    @Override
    public List<User> AfficherCompte() {
        return userRepository.findAll();
    }
}
