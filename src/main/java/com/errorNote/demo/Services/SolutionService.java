package com.errorNote.demo.Services;

import com.errorNote.demo.Modeles.Probleme;
import com.errorNote.demo.Modeles.Solution;

import java.util.List;

public interface SolutionService {
    //METHODE PERMETTANT DE RESOUDRE UN PROBLEME DONNER
    Solution mettreSolution(Solution solution);

    //METHODE PERMETTANT DE MODIFIER UNE SOLUTION DONNER
    Solution modifierSolution(Long id, Solution solution);

    //METHODE PERMETTANT DE TROUVER UNE SOLUTION PAR SON ID
    Solution trouverSolutionParId(Long id);

    //METHODE PERMETTANT DE TROUVER UNE SOLUTION PAR LE PROBLEME
    Solution trouverSolutionParProbleme(Probleme probleme);

    //METHODE PERMETTANT DE VOIR LA SOLUTION D'UN PROBLEME
    List<Solution> voirSolution();
}
