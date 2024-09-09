package com.sistema.transacoes.service;

import com.sistema.transacoes.domain.Cliente;
import com.sistema.transacoes.domain.Empresa;
import com.sistema.transacoes.domain.Transacao;
import com.sistema.transacoes.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private EmpresaService empresaService;

    public Transacao realizarDeposito(Empresa empresa, Cliente cliente, double valor) {
        double taxa = empresa.getTaxa();
        double valorFinal = valor - taxa;
        if (valorFinal <= 0) {
            throw new IllegalArgumentException("O valor do deposito deve ser maior que a taxa");
        }
        Transacao transacao = new Transacao(empresa, cliente, valorFinal, LocalDateTime.now(), "Deposito");
        transacaoRepository.save(transacao);

        empresaService.atualizarSaldo(empresa.getId(), valorFinal);

        return transacao;
    }

    public Transacao realizarSaque(Empresa empresa, Cliente cliente, double valor) {
        if (empresa.getSaldo() <= valor) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar saque");
        }

        Transacao transacao = new Transacao(empresa, cliente, valor, LocalDateTime.now(), "Saque");
        transacaoRepository.save(transacao);

        empresaService.atualizarSaldo(empresa.getId(), -valor);
        return transacao;

    }


}
