package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.Solution;
import com.errorNote.demo.Modeles.User;
import com.errorNote.demo.Services.SolutionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/solution")
public class SolutionController {
    @Autowired
    final private SolutionService solutionService;

    @PostMapping("/poser_solution")
    public Solution poserSolution(Solution solution)
    {
        return solutionService.mettreSolution(solution);
    }

    @GetMapping("/voir_solution")
    public List<Solution> voirSolution()
    {
        return solutionService.voirSolution();
    }

    @PutMapping("/modifier_solution/{idSolution}")
    public Solution modifierSolution(Long idSolution, Solution solution)
    {
        return solutionService.modifierSolution(idSolution,solution);
    }
}
