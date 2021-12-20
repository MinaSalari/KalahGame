package com.minasalari.kalah.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameController gameController;

    @Test
    void testStartNewGame_when_requestFormatIsCorrect() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/kalahGame/start").header("Content-Type", "application/json")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void testPlay_when_requestFormatIsCorrect() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/kalahGame/play/1/3").header("Content-Type", "application/json").header("Game-Ticket", "123")).
                andExpect(status().is2xxSuccessful());
    }

    @Test
    void testPlay_when_gameTicketIsNotSetInHeader() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.put("/kalahGame/play/1/3").header("Content-Type", "application/json")).
                andExpect(status().is4xxClientError());
    }
}