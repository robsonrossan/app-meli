package com.br.meli.apphackerrank.service;

import com.br.meli.apphackerrank.dto.ProdutoRequestDTO;
import com.br.meli.apphackerrank.dto.ProdutoResponseDTO;
import com.br.meli.apphackerrank.entity.ProdutoEntity;
import com.br.meli.apphackerrank.exception.RecursoNaoEncontradoException;
import com.br.meli.apphackerrank.mapper.ProdutoMapper;
import com.br.meli.apphackerrank.repository.ProdutoRepository;
import com.br.meli.apphackerrank.service.impl.ProdutoServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProdutoServiceTest {

    private ProdutoRepository repository;
    private ProdutoMapper mapper;
    private ProdutoServiceImpl service;

    @BeforeEach
    void setup() {
        repository = Mockito.mock(ProdutoRepository.class);
        mapper = Mockito.mock(ProdutoMapper.class);
        service = new ProdutoServiceImpl(repository, mapper);
    }

    @Test
    void deveCadastrarProduto() {
        ProdutoRequestDTO dto = new ProdutoRequestDTO(
                "TV", "Smart", 3000.0, 5, "Loja", "url"
        );

        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(1L);

        ProdutoResponseDTO response = new ProdutoResponseDTO(
                1L, "TV", "Smart", 3000.0, 5, "Loja", "url"
        );

        Mockito.when(mapper.toEntity(dto)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(mapper.toResponseDTO(entity)).thenReturn(response);

        ProdutoResponseDTO resultado = service.cadastrarProduto(dto);

        Assertions.assertEquals(1L, resultado.id());
    }

    @Test
    void deveBuscarProdutoPorId() {
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(1L);
        entity.setTitulo("TV");

        ProdutoResponseDTO response = new ProdutoResponseDTO(
                1L, "TV", "Smart", 2000.0, 3, "Loja", "url"
        );

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(mapper.toResponseDTO(entity)).thenReturn(response);

        ProdutoResponseDTO resultado = service.buscarProdutoPorId(1L);

        Assertions.assertEquals("TV", resultado.titulo());
    }

    @Test
    void deveLancarExcecaoAoBuscarIdInexistente() {
        Mockito.when(repository.findById(99L)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarProdutoPorId(99L)
        );
    }
}
