package com.br.meli.apphackerrank.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoRequestDTO(

        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        Double preco,

        @NotNull(message = "A quantidade é obrigatória")
        @PositiveOrZero(message = "Estoque deve ser >= zero")
        Integer quantidadeEstoque,

        @NotBlank(message = "O vendedor é obrigatório")
        String vendedor,

        @NotBlank(message = "URL da imagem é obrigatória")
        String urlImagem
) {}
