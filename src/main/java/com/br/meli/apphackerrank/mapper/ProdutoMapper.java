package com.br.meli.apphackerrank.mapper;

import com.br.meli.apphackerrank.dto.ProdutoRequestDTO;
import com.br.meli.apphackerrank.dto.ProdutoResponseDTO;
import com.br.meli.apphackerrank.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoEntity toEntity(ProdutoRequestDTO dto);

    ProdutoResponseDTO toResponseDTO(ProdutoEntity entity);

    void updateEntityFromDto(ProdutoRequestDTO dto, @MappingTarget ProdutoEntity entity);
}
