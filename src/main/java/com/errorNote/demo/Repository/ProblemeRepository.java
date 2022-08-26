package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemeRepository extends JpaRepository<Probleme,Long> {
}
