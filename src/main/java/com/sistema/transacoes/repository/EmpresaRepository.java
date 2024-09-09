package com.sistema.transacoes.repository;

import com.sistema.transacoes.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findByCnpj(String cnpj);
}
