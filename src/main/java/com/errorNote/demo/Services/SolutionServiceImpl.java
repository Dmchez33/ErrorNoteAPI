package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Solution;
import com.errorNote.demo.Repository.SolutionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SolutionServiceImpl implements SolutionService{
    @Autowired
    final private SolutionRepository solutionRepository;
    @Override
    public Solution mettreSolution(Solution solution) {

        return solutionRepository.save(solution);

    }

    @Override
    public Solution modifierSolution(Long id, Solution solution) {
        return solutionRepository.findById(id).map(solution1 -> {
            solution1.setDescription(solution.getDescription());
            solution1.setResource(solution.getResource());
            solution1.setDateSolution(solution.getDateSolution());
            solution1.setTempsConsacrer(solution.getTempsConsacrer());
            return solutionRepository.save(solution1);
        }).orElseThrow(()-> new RuntimeException("La solution Choisie n'est pas modifiable"));
    }

    @Override
    public List<Solution> voirSolution() {
        return solutionRepository.findAll();
    }
}
