package com.sistema.transactionspring.service;

import com.sistema.transactionspring.domain.Cliente;
import com.sistema.transactionspring.domain.Empresa;
import com.sistema.transactionspring.domain.Transacao;
import com.sistema.transactionspring.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TransacaoService {
    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private ClienteService clienteService;

    private final RestTemplate restTemplate = new RestTemplate();

    public Transacao realizarDeposito(Empresa empresa, Cliente cliente, double valor) {
        double taxa = empresa.getTaxa();
        double valorFinal = valor - taxa;
        if (valorFinal <= 0) {
            throw new IllegalArgumentException("O valor do deposito deve ser maior que a taxa");
        }
        Transacao transacao = new Transacao(valorFinal, LocalDateTime.now(), "Deposito", empresa, cliente);
        transacaoRepository.save(transacao);

        empresaService.atualizarSaldo(empresa.getId(), valorFinal);
        clienteService.atualizarSaldo(cliente.getId(), valorFinal);

        enviarCallback(transacao);

        notificarCliente(cliente.getEmail(), transacao);

        return transacao;
    }

    public Transacao realizarSaque(Empresa empresa, Cliente cliente, double valor) {
        if (empresa.getSaldo() <= valor) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar saque");
        }

        Transacao transacao = new Transacao(valor, LocalDateTime.now(), "Saque", empresa, cliente);
        transacaoRepository.save(transacao);

        empresaService.atualizarSaldo(empresa.getId(), -valor);
        clienteService.atualizarSaldo(cliente.getId(), -valor);

        enviarCallback(transacao);

        notificarCliente(cliente.getEmail(), transacao);

        return transacao;
    }
    private void enviarCallback(Transacao transacao) {
        String callbackUrl = "https://webhook.site/32fb7a9a-8c74-4fa4-91de-f9c89499c12e"; // Substitua pela URL do webhook

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(callbackUrl, transacao, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Callback enviado com sucesso!");
            } else {
                System.err.println("Erro ao enviar callback: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Erro ao enviar callback: " + e.getMessage());
        }
    }

    private void notificarCliente(String email, Transacao transacao) {
        System.out.println("Notificando cliente " + email + " sobre a transação: " + transacao);
    }

}
