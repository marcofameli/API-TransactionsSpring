package com.sistema.transacoes.service;

import com.sistema.transacoes.domain.Cliente;
import com.sistema.transacoes.domain.Empresa;
import com.sistema.transacoes.domain.Transacao;
import com.sistema.transacoes.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private EmpresaService empresaService;

    @Mock
    private ClienteService clienteService;

    private Empresa empresa;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar os objetos de Empresa e Cliente para os testes
        empresa = new Empresa();
        empresa.setId(1L);
        empresa.setCnpj("12345678000195");
        empresa.setTaxa(1.0);
        empresa.setSaldo(1000.0);

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678909");
        cliente.setSaldo(500.0);
    }

    @Test
    @DisplayName("Deve realizar um depósito e atualizar saldos da empresa e do cliente")
    void testRealizarDeposito() {

        when(empresaService.buscarPorCnpj("12345678000195")).thenReturn(empresa);
        when(clienteService.buscarClientePorCpf("12345678909")).thenReturn(cliente);


        Transacao transacao = transacaoService.realizarDeposito(empresa, cliente, 100.0);

        assertNotNull(transacao);
        assertEquals(99.0, transacao.getValor(), "O valor da transação deve ser 99.0 após a taxa");
        assertEquals("Deposito", transacao.getTipo());
        verify(transacaoRepository).save(transacao);
        verify(empresaService).atualizarSaldo(1L, 99.0);
        verify(clienteService).atualizarSaldo(1L, 99.0);
    }

    @Test
    @DisplayName("Deve realizar um saque e atualizar saldos da empresa e do cliente")
    void testRealizarSaque() {

        when(empresaService.buscarPorCnpj("12345678000195")).thenReturn(empresa);
        when(clienteService.buscarClientePorCpf("12345678909")).thenReturn(cliente);

        Transacao transacao = transacaoService.realizarSaque(empresa, cliente, 100.0);


        assertNotNull(transacao);
        assertEquals(100.0, transacao.getValor());
        assertEquals("Saque", transacao.getTipo());
        verify(transacaoRepository).save(transacao);
        verify(empresaService).atualizarSaldo(1L, -100.0);
        verify(clienteService).atualizarSaldo(1L, -100.0);
    }
    @Test
    @DisplayName("Deve lançar exceção ao tentar realizar saque com saldo insuficiente")
    void testRealizarSaqueComSaldoInsuficiente() {

        double valorSaque = empresa.getSaldo() + 1; // valor acima do saldo
        when(empresaService.buscarPorCnpj("12345678000195")).thenReturn(empresa);
        when(clienteService.buscarClientePorCpf("12345678909")).thenReturn(cliente);


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transacaoService.realizarSaque(empresa, cliente, valorSaque);
        });
        assertEquals("Saldo insuficiente para realizar saque", exception.getMessage());
    }
}
