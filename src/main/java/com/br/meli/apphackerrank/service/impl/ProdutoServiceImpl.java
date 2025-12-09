package com.br.meli.apphackerrank.service.impl;

import com.br.meli.apphackerrank.dto.ProdutoRequestDTO;
import com.br.meli.apphackerrank.dto.ProdutoResponseDTO;
import com.br.meli.apphackerrank.entity.ProdutoEntity;
import com.br.meli.apphackerrank.exception.RecursoNaoEncontradoException;
import com.br.meli.apphackerrank.mapper.ProdutoMapper;
import com.br.meli.apphackerrank.repository.ProdutoRepository;
import com.br.meli.apphackerrank.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoServiceImpl(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ProdutoResponseDTO cadastrarProduto(ProdutoRequestDTO dto) {
        ProdutoEntity produto = mapper.toEntity(dto);
        ProdutoEntity salvo = repository.save(produto);
        return mapper.toResponseDTO(salvo);
    }

    @Override
    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        ProdutoEntity produto = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));

        return mapper.toResponseDTO(produto);
    }

    @Override
    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO dto) {
        ProdutoEntity produto = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado"));

        mapper.updateEntityFromDto(dto, produto);

        ProdutoEntity atualizado = repository.save(produto);
        return mapper.toResponseDTO(atualizado);
    }

    @Override
    public List<ProdutoResponseDTO> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void excluirProduto(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto não encontrado");
        }
        repository.deleteById(id);
    }
}
