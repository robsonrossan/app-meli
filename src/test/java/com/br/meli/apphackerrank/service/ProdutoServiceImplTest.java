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
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ProdutoServiceImplTest {

    @Test
    void deveCadastrarProduto() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ProdutoMapper mapper = Mockito.mock(ProdutoMapper.class);
        ProdutoServiceImpl service = new ProdutoServiceImpl(repository, mapper);

        ProdutoRequestDTO dto = new ProdutoRequestDTO(
                "Celular", "Android", 2500.0, 10, "Loja 1", "http://img.jpg"
        );

        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(1L);

        ProdutoResponseDTO response = new ProdutoResponseDTO(
                1L, "Celular", "Android", 2500.0, 10, "Loja 1", "http://img.jpg"
        );

        Mockito.when(mapper.toEntity(dto)).thenReturn(entity);
        Mockito.when(repository.save(entity)).thenReturn(entity);

        // ✅ MOCK QUE ESTAVA FALTANDO (CAUSA DO ERRO)
        Mockito.when(mapper.toResponseDTO(entity)).thenReturn(response);

        ProdutoResponseDTO resposta = service.cadastrarProduto(dto);

        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(1L, resposta.id());
    }

    @Test
    void deveBuscarProdutoPorId() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ProdutoMapper mapper = Mockito.mock(ProdutoMapper.class);
        ProdutoServiceImpl service = new ProdutoServiceImpl(repository, mapper);

        ProdutoEntity produto = new ProdutoEntity();
        produto.setId(1L);

        ProdutoResponseDTO response = new ProdutoResponseDTO(
                1L, "TV", "Smart", 2000.0, 3, "Loja", "url"
        );

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(produto));
        Mockito.when(mapper.toResponseDTO(produto)).thenReturn(response);

        ProdutoResponseDTO resposta = service.buscarProdutoPorId(1L);

        Assertions.assertNotNull(resposta);
        Assertions.assertEquals(1L, resposta.id());
    }

    @Test
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        ProdutoRepository repository = Mockito.mock(ProdutoRepository.class);
        ProdutoMapper mapper = Mockito.mock(ProdutoMapper.class);
        ProdutoServiceImpl service = new ProdutoServiceImpl(repository, mapper);

        Mockito.when(repository.findById(99L)).thenReturn(Optional.empty());

        Assertions.assertThrows(
                RecursoNaoEncontradoException.class,
                () -> service.buscarProdutoPorId(99L)
        );
    }
}
