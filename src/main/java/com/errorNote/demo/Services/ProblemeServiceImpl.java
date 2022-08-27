package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Repository.ProblemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProblemeServiceImpl implements ProblemeService{

    final private ProblemeRepository problemeRepository;
    @Override
    public Probleme poserProbleme(Probleme probleme) {
        return problemeRepository.save(probleme);
    }

    @Override
    public List<Probleme> voirProbleme() {
        return problemeRepository.findAll();
    }

    @Override
    public Probleme modifierProbleme(Long id,Probleme probleme) {
        return problemeRepository.findById(id).map(probleme1 -> {
            probleme1.setTitre(probleme.getTitre());
            probleme1.setDescription(probleme.getDescription());
            probleme1.setSolution(probleme.getSolution());
            probleme1.setTechnoConcerner(probleme.getTechnoConcerner());
            probleme1.setUser(probleme.getUser());
            probleme1.setDateProbleme(probleme.getDateProbleme());
            return problemeRepository.save(probleme1);
        }).orElseThrow(()-> new RuntimeException("PROBLEME NON RETROUVER"));

    }

    @Override
    public List<Probleme> rechercherProblemeParMot(String mot) {
        return problemeRepository.trouverProblemeParMot(mot);
    }

    @Override
    public Probleme TrouverProblemeParTitre(String titre) {
        return problemeRepository.findByTitre(titre);
    }
}
