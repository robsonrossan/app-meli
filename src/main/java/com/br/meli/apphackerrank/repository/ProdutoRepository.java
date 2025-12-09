package com.br.meli.apphackerrank.repository;

import com.br.meli.apphackerrank.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
}