package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.EtatProbleme;
import com.errorNote.demo.Repository.EtatProblemeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EtatProblemeServiceImpl implements EtatProblemeService {
    @Autowired
    private final EtatProblemeRepository etatProblemeRepository;

    @Override
    public List<EtatProbleme> voirEtat() {
        return etatProblemeRepository.findAll();
    }

    @Override
    public EtatProbleme creerEtat(EtatProbleme etatProbleme) {
        return etatProblemeRepository.save(etatProbleme);
    }

    @Override
    public EtatProbleme modifierEtat(Long idetat, EtatProbleme etatProbleme) {

        return etatProblemeRepository.findById(idetat).map(etat -> {
            etat.setEtat(etatProbleme.getEtat());
            return etatProblemeRepository.save(etat);
        }).orElseThrow(() -> new RuntimeException("L'etat non Trouvé"));

    }
}
