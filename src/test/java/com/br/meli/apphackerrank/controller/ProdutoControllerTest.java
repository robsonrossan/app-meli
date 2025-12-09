package com.br.meli.apphackerrank.controller;

import com.br.meli.apphackerrank.dto.ProdutoRequestDTO;
import com.br.meli.apphackerrank.dto.ProdutoResponseDTO;
import com.br.meli.apphackerrank.exception.RecursoNaoEncontradoException;
import com.br.meli.apphackerrank.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProdutoRequestDTO request;
    private ProdutoResponseDTO response;

    @BeforeEach
    void setup() {
        request = new ProdutoRequestDTO(
                "Celular X", "Modelo novo", 2500.0, 10, "Loja X", "http://img.com"
        );

        response = new ProdutoResponseDTO(
                1L, "Celular X", "Modelo novo", 2500.0, 10, "Loja X", "http://img.com"
        );
    }

    @Test
    void deveCadastrarProduto() throws Exception {
        Mockito.when(produtoService.cadastrarProduto(any())).thenReturn(response);

        mockMvc.perform(post("/produtos")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deveRetornar400AoCadastrarInvalido() throws Exception {
        ProdutoRequestDTO invalido = new ProdutoRequestDTO(null, null, null, null, null, null);

        mockMvc.perform(post("/produtos")
                        .content(objectMapper.writeValueAsString(invalido))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").exists());
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {
        Mockito.when(produtoService.buscarProdutoPorId(1L)).thenReturn(response);

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deveRetornar404AoBuscarProdutoInexistente() throws Exception {
        Mockito.when(produtoService.buscarProdutoPorId(99L))
                .thenThrow(new RecursoNaoEncontradoException("Produto não encontrado"));

        mockMvc.perform(get("/produtos/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value("Produto não encontrado"));
    }

    @Test
    void deveBuscarTodosProdutos() throws Exception {
        Mockito.when(produtoService.buscarTodos()).thenReturn(List.of(response));

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void deveBuscarListaVazia() throws Exception {
        Mockito.when(produtoService.buscarTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void deveAtualizarProduto() throws Exception {
        Mockito.when(produtoService.atualizarProduto(anyLong(), any())).thenReturn(response);

        mockMvc.perform(put("/produtos/1")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deveExcluirProduto() throws Exception {
        mockMvc.perform(delete("/produtos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404AoExcluirProdutoInexistente() throws Exception {
        Mockito.doThrow(new RecursoNaoEncontradoException("Produto não encontrado"))
                .when(produtoService).excluirProduto(99L);

        mockMvc.perform(delete("/produtos/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.erro").value("Produto não encontrado"));
    }
}
