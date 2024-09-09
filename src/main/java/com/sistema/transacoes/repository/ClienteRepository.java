package com.sistema.transacoes.repository;

import com.sistema.transacoes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpf(String cpf);
}
