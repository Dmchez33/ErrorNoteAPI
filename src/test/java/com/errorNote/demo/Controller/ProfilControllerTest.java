package com.errorNote.demo.Controller;

import com.errorNote.demo.Repository.UserRepository;
import com.errorNote.demo.Services.ProfilService;
import com.errorNote.demo.Services.UserService;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.hamcrest.CoreMatchers.is;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfilControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    private JSONObject json;



    @Test
    public void testGetProfil() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/profil/ajouter_profil")
                        .content(
                                "{" +   "\"libelle\":\"User\""
                                    +   "}"
                        ).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.libelle", is("User")));
    }
}
