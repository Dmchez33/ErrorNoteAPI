package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
    Commentaire findByIdCom(Long idCom);
}
