package com.errorNote.demo.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
Les annotations @SpringBootTest et @AutoConfigureMockMvc
permettent de charger le contexte Spring et de réaliser
des requêtes sur le controller.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CommentaireControllerTest {
    /*{
        "descriptionc":"vvvvvvvvvvvvvvvvvvvvvvvvvvvv"
    }*/
    @Autowired
    private MockMvc mockMvc;

    @Test
    void commenter() throws Exception {

        mockMvc.perform(post("/commentaire/commenter/diarraidrissa9@gmail.com/123456789/ERREUR BEAN")
                        .content(
                                "{"
                                        + "\"descriptionc\": \"wwwwwwwwwwwwwwwwwww\""
                                        + "}"
                        ).contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void voirCommentaire() throws Exception {
        mockMvc.perform(get("/commentaire/affichercommentaire")

                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void supprimerCommentaire() {
    }

    @Test
    void supprimerCommentaireParAdmin() {
    }
}