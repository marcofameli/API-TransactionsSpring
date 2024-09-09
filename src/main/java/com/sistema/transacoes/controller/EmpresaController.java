package com.sistema.transacoes.controller;

import com.sistema.transacoes.domain.Empresa;
import com.sistema.transacoes.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/empresas")

public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa empresa) {
        Empresa novaEmpresa = empresaService.criar(empresa);
        return ResponseEntity.ok(novaEmpresa);
    }
    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        List <Empresa> empresas = empresaService.buscarTodas();
        return ResponseEntity.ok().body(empresas);
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<Empresa> buscarEmpresaPorCnpj(@PathVariable String cnpj) {
        Empresa empresa = empresaService.buscarPorCnpj(cnpj);
        if (empresa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa (@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
