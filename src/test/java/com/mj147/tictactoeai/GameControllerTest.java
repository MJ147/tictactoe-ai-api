package com.mj147.tictactoeai;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void createGames() throws Exception {
        mockMvc.perform(post("/game/create"));
        mockMvc.perform(post("/game/create"));
        mockMvc.perform(post("/game/create"));
    }

    @Test
    public void ifCreateRequestReturn200() throws Exception {
        mockMvc.perform(post("/game/create"))
                .andExpect(status().isOk());
    }

    @Test
    public void ifGetRequestReturn200() throws Exception {
        mockMvc.perform(get("/game/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void ifDeleteRequestReturn200() throws Exception {
        mockMvc.perform(delete("/game/{id}", 3))
                .andExpect(status().isOk());

    }

    @Test
    public void ifCheckRequestReturn200() throws Exception {
        mockMvc.perform(put("/player/move/{id}", 1));
        mockMvc.perform(put("/player/move/{id}", 2));
        mockMvc.perform(put("/player/move/{id}", 5));
        mockMvc.perform(put("/player/move/{id}", 3));
        mockMvc.perform(put("/player/move/{id}", 9));
        mockMvc.perform(get("/game/check/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));

    }
}
