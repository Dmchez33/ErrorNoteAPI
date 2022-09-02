package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil,Long> {

    Profil findByLibelle(String libelle);
}
