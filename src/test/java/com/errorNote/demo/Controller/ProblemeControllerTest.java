package com.errorNote.demo.Controller;

import org.junit.jupiter.api.*;
import com.errorNote.demo.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Les annotations @SpringBootTest et @AutoConfigureMockMvc
permettent de charger le contexte Spring et de réaliser
des requêtes sur le controller.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ProblemeControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void poserProbleme() throws Exception{
        mockMvc.perform(post("/probleme/poser_probleme/kali@gmail.com/AJOUR2345")
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

    @Test
    void modifierProbleme() throws Exception {

        mockMvc.perform(put("/probleme/modifier_probleme/kali@gmail.com/AJOUR2345/ERREUR UNKNOWN/Résolu")
                        .content(
                                "{"
                                        + "\"titre\": \"ERREUR UNKNOWN23\","
                                        + "\"description\":\"J'ai un probleme lors du mise en place \","
                                        + "\"techno\":\"PHP\""
                                        + "}"
                        ).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}