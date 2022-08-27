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
    @Query(value = "SELECT titre, description, techno, date_probleme FROM probleme WHERE (titre LIKE %:value%) OR (description LIKE %:value%) OR (techno LIKE %:value%)",nativeQuery = true)
    List<Object> trouverProblemeParMot(@Param("value") String value);

    Probleme findByTitre(String titre);

    Probleme findByUser(User user);

}
