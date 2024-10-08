package com.sistema.transactionspring.repository;

import com.sistema.transactionspring.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
