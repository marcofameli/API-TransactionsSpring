package com.sistema.transacoes.controller;

import com.sistema.transacoes.domain.Cliente;
import com.sistema.transacoes.domain.Empresa;
import com.sistema.transacoes.domain.Transacao;
import com.sistema.transacoes.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping("/deposito")
    public ResponseEntity<Transacao> realizarDeposito(@RequestParam Long empresaId, @RequestParam Long clienteId, @RequestParam double valor) {
        Empresa empresa = new Empresa(); // Aqui você deve buscar a empresa pelo ID
        empresa.setId(empresaId);

        Cliente cliente = new Cliente(); // Aqui você deve buscar o cliente pelo ID
        cliente.setId(clienteId);

        Transacao transacao = transacaoService.realizarDeposito(empresa, cliente, valor);
        return ResponseEntity.ok(transacao);
    }

    @PostMapping("/saque")
    public ResponseEntity<Transacao> realizarSaque(@RequestParam Long empresaId, @RequestParam Long clienteId, @RequestParam double valor) {
        Empresa empresa = new Empresa(); // Aqui você deve buscar a empresa pelo ID
        empresa.setId(empresaId);

        Cliente cliente = new Cliente(); // Aqui você deve buscar o cliente pelo ID
        cliente.setId(clienteId);

        Transacao transacao = transacaoService.realizarSaque(empresa, cliente, valor);
        return ResponseEntity.ok(transacao);
    }
}
