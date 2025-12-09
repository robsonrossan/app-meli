package com.br.meli.apphackerrank.service;

import com.br.meli.apphackerrank.dto.ProdutoRequestDTO;
import com.br.meli.apphackerrank.dto.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoService {

    ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto);

    ProdutoResponseDTO buscarProdutoPorId(Long id);

    ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto);

    List<ProdutoResponseDTO> buscarTodos();

    void excluirProduto(Long id);
}
