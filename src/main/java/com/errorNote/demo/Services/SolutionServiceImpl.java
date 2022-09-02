package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Solution;
import com.errorNote.demo.Repository.SolutionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SolutionServiceImpl implements SolutionService {
    //ISTANCIATION D'UN REPOSITORY DE TYPE SOLUTION
    @Autowired
    final private SolutionRepository solutionRepository;

    //IMPLEMENTATION DE L'AJOUT D'UN SYSTEME DANS LE SYSTEME
    @Override
    public Solution mettreSolution(Solution solution) {

        return solutionRepository.save(solution);

    }

    //IMPLEMENTATION METHODE PERMETTANT DE MODIFIER LA SOLUTION D'UN PROBLEME
    @Override
    public Solution modifierSolution(Long id, Solution solution) {
        return solutionRepository.findById(id).map(solution1 -> {
            solution1.setDescriptions(solution.getDescriptions());
            solution1.setResource(solution.getResource());
            solution1.setDateSolution(solution.getDateSolution());
            solution1.setTempsconsacrer(solution.getTempsconsacrer());
            return solutionRepository.save(solution1);
        }).orElseThrow(() -> new RuntimeException("LA SOLUTION CHOISIE N'EST PAS MODIFIABLE"));
    }

    //IMPLEMENTATION METHODE PERMETTANT DE TROUVER UNE SOLUTION PAR SON ID
    @Override
    public Solution trouverSolutionParId(Long id) {
        return solutionRepository.findByIdc(id);
    }

    //IMPLEMENTATION DE LA METHODE PERMETTANT DE TROUVER UNE SOLUTION PAR LE PROBLEME
    @Override
    public Solution trouverSolutionParProbleme(Probleme probleme) {
        return solutionRepository.findByProbleme(probleme);
    }

    //IMPLEMENTATION METHODE PERMETTANT DE VOIR LA SOLUTION DES PROBLEME
    @Override
    public List<Solution> voirSolution() {
        return solutionRepository.findAll();
    }
}
