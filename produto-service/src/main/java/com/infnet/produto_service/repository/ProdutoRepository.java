package com.infnet.produto_service.repository;

import com.infnet.produto_service.model.Produto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProdutoRepository extends R2dbcRepository<Produto, Long> {
}
