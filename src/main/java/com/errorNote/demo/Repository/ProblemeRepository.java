package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProblemeRepository extends JpaRepository<Probleme,Long> {
    //REQUETTE PERMETTANT DE RECUPERER UN PROBLEME SANS LES SOLUTIONS ET LES COMMENTAIRE
    @Query(value = "SELECT titre, description, techno, date_probleme FROM probleme WHERE (titre LIKE %:value%) OR (description LIKE %:value%) OR (techno LIKE %:value%)",nativeQuery = true)
    List<Object> trouverProblemeParMot(@Param("value") String value);

    //REQUETTE PERMETTANT DE RETOURNE DES PROBLEMME ET LEURS SOLUTION ET LES COMMENTAIRES ASSOCIER
    @Query(value = "SELECT titre, description, techno, date_probleme,descriptions,resource,tempsconsacrer,methoderecherche,date_solution, descriptionc, date_commentaire FROM probleme,solution,Commentaire WHERE ((probleme.idp = solution.id_probleme) AND (solution.idc = commentaire.id_solution) ) AND ((titre LIKE %:value%) OR (description LIKE %:value%) OR (techno LIKE %:value%))",nativeQuery = true)
    List<Object> trouverProblemeSolutionCommentaireParMot(@Param("value") String value);


    Probleme findByTitre(String titre);

    Probleme findByUser(User user);

}
