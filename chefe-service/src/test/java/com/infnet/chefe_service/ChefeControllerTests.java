package com.infnet.chefe_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.chefe_service.controller.ChefeController;
import com.infnet.chefe_service.model.Chefe;
import com.infnet.chefe_service.service.ChefeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChefeController.class)
public class ChefeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChefeService chefeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllChefes() throws Exception {
        Chefe chefe = new Chefe(1, "João", "Silva");
        List<Chefe> chefes = List.of(chefe);

        when(chefeService.getAll()).thenReturn(chefes);

        mockMvc.perform(MockMvcRequestBuilders.get("/chefe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[0].sobrenome").value("Silva"))
                .andDo(print());
    }

    @Test
    public void testGetChefeById() throws Exception {
        Chefe chefe = new Chefe(1, "João", "Silva");

        when(chefeService.findById(1)).thenReturn(Optional.of(chefe));

        mockMvc.perform(MockMvcRequestBuilders.get("/chefe/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.sobrenome").value("Silva"))
                .andDo(print());
    }

    @Test
    public void testGetChefeByIdNotFound() throws Exception {
        when(chefeService.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/chefe/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void testSaveChefe() throws Exception {
        Chefe chefe = new Chefe(0, "João", "Silva");

        mockMvc.perform(MockMvcRequestBuilders.post("/chefe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chefe)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Criado com sucesso"))
                .andDo(print());

        verify(chefeService, times(1)).save(chefe);
    }

    @Test
    public void testUpdateChefe() throws Exception {
        Chefe chefe = new Chefe(1, "João", "Silva");

        when(chefeService.update(eq(1), any(Chefe.class))).thenReturn(chefe);

        mockMvc.perform(MockMvcRequestBuilders.put("/chefe/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(chefe)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Atualizado com sucesso"))
                .andDo(print());

        verify(chefeService, times(1)).update(eq(1), any(Chefe.class));
    }

    @Test
    public void testDeleteChefe() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/chefe/1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Deletado com sucesso"))
                .andDo(print());

        verify(chefeService, times(1)).deleteById(1);
    }
}
