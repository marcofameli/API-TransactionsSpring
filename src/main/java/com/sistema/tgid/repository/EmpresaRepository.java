package com.sistema.tgid.repository;

import com.sistema.tgid.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Empresa findByCnpj(String cnpj);
}
