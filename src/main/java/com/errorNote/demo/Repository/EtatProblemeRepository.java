package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.EtatProbleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtatProblemeRepository extends JpaRepository<EtatProbleme,Long> {
}
