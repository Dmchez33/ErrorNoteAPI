package com.errorNote.demo.Repository;

import com.errorNote.demo.Modeles.Profil;
import com.errorNote.demo.Modeles.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByPassword(String password);
    User findByEmail(String email);

    User findByProfil(Profil profil);
    User findByEmailAndPassword(String email,String password);
}
