package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Commentaire;

import java.util.List;

public interface CommentaireService {
    Commentaire commenter(Commentaire commentaire);
    List<Commentaire> voirCommentaire();
    String supprimerCommentaire(Long idCom);
    Commentaire trouverParId(Long idCom);
}
