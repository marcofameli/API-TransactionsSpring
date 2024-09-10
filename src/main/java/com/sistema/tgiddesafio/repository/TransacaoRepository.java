package com.sistema.tgiddesafio.repository;

import com.sistema.tgiddesafio.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
