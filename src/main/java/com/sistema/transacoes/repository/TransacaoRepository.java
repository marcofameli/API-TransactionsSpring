package com.sistema.transacoes.repository;

import com.sistema.transacoes.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
