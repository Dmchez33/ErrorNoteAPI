package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Commentaire;
import com.errorNote.demo.Repository.CommentaireRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CommentaireServiceImpl implements CommentaireService {
    @Autowired
    final private CommentaireRepository commentaireRepository;
    @Override
    public Commentaire commenter(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> voirCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public String supprimerCommentaire(Long idCom) {
        commentaireRepository.deleteById(idCom);
        return"Commentaire supprimer avec succes";
    }

    @Override
    public Commentaire trouverParId(Long idCom) {
        return commentaireRepository.findByIdCom(idCom);
    }
}
