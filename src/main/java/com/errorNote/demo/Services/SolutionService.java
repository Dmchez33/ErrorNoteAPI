package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Solution;

import java.util.List;

public interface SolutionService {
    Solution mettreSolution(Solution solution);
    Solution modifierSolution(Long id, Solution solution);
    List<Solution> voirSolution();
}
