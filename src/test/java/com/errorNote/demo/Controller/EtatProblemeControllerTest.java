package com.errorNote.demo.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EtatProblemeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ajouterEtat() throws Exception {
        mockMvc.perform(post("/etat/ajouter_etat/diarraidrissa9@gmail.com/123456789")
                        .content(
                                "{"
                                        + "\"etat\": \"En cours\""
                                        + "}"
                        ).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void modifierEtat() throws Exception {
        long idEtat = 3;
        mockMvc.perform(put("/etat/modifier_etat/diarraidrissa9@gmail.com/123456789/" + idEtat)
                        .content(
                                "{"
                                        + "\"etat\": \"Fermé\""

                                        + "}"
                        ).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void voirEtat() throws Exception {
        mockMvc.perform(get("/etat/voirEtat")

                )
                .andDo(print())
                .andExpect(status().isOk());
    }
}