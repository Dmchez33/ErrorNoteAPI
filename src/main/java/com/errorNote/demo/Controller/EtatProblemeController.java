package com.errorNote.demo.Controller;

import com.errorNote.demo.Modeles.EtatProbleme;
import com.errorNote.demo.Services.EtatProblemeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etat")
public class EtatProblemeController {
    @Autowired
    final private EtatProblemeService etatProblemeService;

    @PostMapping("/ajouter_etat")
    public EtatProbleme ajouterEtat(EtatProbleme etatProbleme)
    {
        return etatProblemeService.creerEtat(etatProbleme);
    }

    @PutMapping("/modifier_etat/{idEtat}")
    public EtatProbleme modifierEtat(Long idetat, EtatProbleme etatProbleme)
    {
        return etatProblemeService.modifierEtat(idetat, etatProbleme);
    }

    @GetMapping("/voirEtat")
    public List<EtatProbleme> voirEtat(){
        return etatProblemeService.voirEtat();
    }
}
