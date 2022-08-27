package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProblemeRepository extends JpaRepository<Probleme,Long> {
    @Query(value = "SELECT titre, description, techno_concerner, date_probleme FROM probleme WHERE titre OR description OR techno_concerner LIKE '%value%'",nativeQuery = true)
    List<Probleme> trouverProblemeParMot(@Param("value") String value);

    Probleme findByTitre(String titre);
}
