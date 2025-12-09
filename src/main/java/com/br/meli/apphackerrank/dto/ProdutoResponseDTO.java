package com.br.meli.apphackerrank.dto;

public record ProdutoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        Double preco,
        Integer quantidadeEstoque,
        String vendedor,
        String urlImagem
) {}
