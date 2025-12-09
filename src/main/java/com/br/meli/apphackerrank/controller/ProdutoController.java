package com.br.meli.apphackerrank.controller;

import com.br.meli.apphackerrank.dto.ProdutoRequestDTO;
import com.br.meli.apphackerrank.dto.ProdutoResponseDTO;
import com.br.meli.apphackerrank.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> cadastrar(@Validated @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(service.cadastrarProduto(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarProdutoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id,
                                                        @Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarProduto(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}