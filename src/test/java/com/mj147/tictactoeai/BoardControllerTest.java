package com.mj147.tictactoeai;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void ifCreateBoardIsCorrectReturn200AndBody() throws Exception {
        mockMvc.perform(post("/board/create"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                                "\t\"squares\": [0,0,0,0,0,0,0,0,0]\n" +
                                "}")

                );
    }

    @Test
    public void ifGetBoardIsCorrectReturn200AndBody() throws Exception {
        mockMvc.perform(post("/board/create"));
        mockMvc.perform(get("/board/{id}", 1))
                .andExpect(status().isOk());

    }

    @Test
    public void ifUpdateBoardIsCorrectReturn200AndBody() throws Exception {
        mockMvc.perform(post("/board/create"));
        mockMvc.perform(post("/board/create"));
        mockMvc.perform(put("/board/update")
                .content("{\n" +
                        "\t\"id\": 2,\n" +
                        "\t\"squares\": [0,0,0,1,0,0,0,0,0]\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}
