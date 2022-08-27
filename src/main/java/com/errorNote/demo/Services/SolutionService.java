package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Solution;

import java.util.List;

public interface SolutionService {
    Solution mettreSolution(Solution solution);
    Solution modifierSolution(Long id, Solution solution);
    Solution trouverSolutionParId(Long id);
    Solution trouverSolutionParProbleme(Probleme probleme);
    List<Solution> voirSolution();
}
