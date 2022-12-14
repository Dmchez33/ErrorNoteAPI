package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution,Long > {

    Solution findByIdc(Long id);
    Solution findByProbleme(Probleme probleme);
}
