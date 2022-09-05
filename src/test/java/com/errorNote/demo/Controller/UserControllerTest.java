package com.errorNote.demo.Controller;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/*
Les annotations @SpringBootTest et @AutoConfigureMockMvc
permettent de charger le contexte Spring et de réaliser
des requêtes sur le controller.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private JSONObject json;
    @Test
    void creerCompte() throws Exception {
        this.mockMvc.perform(post("/user/creer_compte_User")
                        .content(
                                "{"
                                    +   "\"nom\": \"MALLE\","
                                    +   "\"prenom\": \"Lassi\","
                                    +   "\"contact\": \"+223 238902\","
                                    +   "\"email\": \"lassi@gmail.com\","
                                    +   "\"password\": \"90ER1234\""
                                    +    "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void creerCompteAdmin() throws Exception{
         MvcResult result =  mockMvc.perform(post("/user/creer_compte_User").content(
                        "{"     +   "\"nom\": \"MALLE1\","
                                +   "\"prenom\": \"Kadi\","
                                +   "\"contact\": \"+223 238945\","
                                +   "\"email\": \"kadidiam901@gmail.com\","
                                +   "\"password\": \"kaze2345621ZAM\""
                                +    "}"
                ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
         json = new JSONObject(result.getResponse().getContentAsString());


    }

    @Test
    void modifierCompte() throws Exception{
        long idUser = 8;
       mockMvc.perform(put("/user/modifier_compte/kadidiam13@gmail.com/kaze234561345/Admin/"+idUser).content(
                        "{"
                                +   "\"idUser\":" +idUser+","
                                +   "\"nom\": \"MALLE2\","
                                +   "\"prenom\": \"Kadidia\","
                                +   "\"contact\": \"+223 238945\","
                                +   "\"email\": \"kadidiam100@gmail.com\","
                                +   "\"password\": \"kaze2345613451234\""
                                +    "}"
                ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void supprimerCompte() throws Exception {
        long id = json.getLong("idUser");
        mockMvc.perform(delete("/user/supprimer_compte/kadidiam13@gmail.com/kaze234561345/Admin/" + id))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void compte() {
    }


    @Test
    void afficher() throws Exception {
        mockMvc.perform(get("/user/afficher")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom", is("KONATE")));
    }
}