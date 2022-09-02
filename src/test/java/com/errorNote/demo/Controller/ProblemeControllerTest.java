package com.errorNote.demo.Controller;

import com.errorNote.demo.Services.EtatProblemeService;
import com.errorNote.demo.Services.ProblemeService;
import com.errorNote.demo.Services.SolutionService;
import com.errorNote.demo.Services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(controllers = ProblemeController.class)
@SpringBootTest
@AutoConfigureMockMvc
class ProblemeControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void poserProbleme() throws Exception{
        mockMvc.perform(post("/probleme/poser_probleme/diarradiaraba9@gmail.com/123456789")
                        .content(
                                "{"
                                        + "\"titre\": \"ERREUR BEAN\","
                                        + "\"description\":\"PROBLEME DE BEAN \","
                                        + "\"techno\":\"SPRING BOOT\""
                                        + "}"
                        ).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }
   /* @Test
    void poserProbleme() throws Exception {
        mockMvc.perform(post("/probleme/poser_probleme/diarradiaraba9@gmail.com/123456789").content("{"
                        + "\"titre\":\"Gestion de dépendance dans laravel\","
                        +
                        "\"description\":\"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\","
                        +
                        "\"techno\":\"laravel\""
                        +
                        "}").contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }*/

    @Test
    void voirProbleme() throws Exception {
        mockMvc.perform(get("/probleme/voir_probleme"))
                .andExpect(status().isOk());
    }

    @Test
    void modifierProbleme() throws Exception {
        mockMvc.perform(put("/probleme/modifier_probleme/diarradiaraba9@gmail.com/123456789/Gestion de dépendance dans Ruby/Initié").content(
                        "{"
                                + "\"titre\": \"Gestion de dépendance dans laravel\","
                                + "\"description\": \"YYYYYYYYYYYYYYYYYYYYYYYYY\","
                                + "\"techno\": \"laravel\""

                                + "}"
                ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void trouverProblemeParMotCle() throws Exception {
        mockMvc.perform(get("/probleme/trouver_par_mot/larvel"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void trouverProblemeSolutionCommentaireParMotCle() throws Exception {
        mockMvc.perform(get("/probleme/trouver_par_mot_probleme_commentaire/Ruby"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}