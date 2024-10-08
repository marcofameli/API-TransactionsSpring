package com.sistema.transactionspring.controller;

import com.sistema.transactionspring.DTO.req.DepositoRequestDTO;
import com.sistema.transactionspring.DTO.req.SaqueRequestDTO;
import com.sistema.transactionspring.DTO.res.TransacaoResponseDTO;
import com.sistema.transactionspring.domain.Cliente;
import com.sistema.transactionspring.domain.Empresa;
import com.sistema.transactionspring.domain.Transacao;
import com.sistema.transactionspring.service.ClienteService;
import com.sistema.transactionspring.service.EmpresaService;
import com.sistema.transactionspring.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/deposito")
    public ResponseEntity<TransacaoResponseDTO> realizarDeposito(@RequestBody DepositoRequestDTO depositoRequestDTO) {
        // Aqui você deve buscar a empresa e o cliente pelos seus respectivos serviços
        Empresa empresa = buscarEmpresaPorId(depositoRequestDTO.getEmpresaId());
        Cliente cliente = buscarClientePorId(depositoRequestDTO.getClienteId());

        Transacao transacao = transacaoService.realizarDeposito(empresa, cliente, depositoRequestDTO.getValor());

        // Converte a transação para o DTO de resposta
        TransacaoResponseDTO responseDTO = new TransacaoResponseDTO();
        responseDTO.setId(transacao.getId());
        responseDTO.setEmpresaId(empresa.getId());
        responseDTO.setClienteId(cliente.getId());
        responseDTO.setValor(transacao.getValor());
        responseDTO.setData(transacao.getData());
        responseDTO.setTipo("DEPOSITO");

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/saque")
    public ResponseEntity<TransacaoResponseDTO> realizarSaque(@RequestBody SaqueRequestDTO saqueRequestDTO) {
        // Aqui você deve buscar a empresa e o cliente pelos seus respectivos serviços
        Empresa empresa = buscarEmpresaPorId(saqueRequestDTO.getEmpresaId());
        Cliente cliente = buscarClientePorId(saqueRequestDTO.getClienteId());

        Transacao transacao = transacaoService.realizarSaque(empresa, cliente, saqueRequestDTO.getValor());

        // Converte a transação para o DTO de resposta
        TransacaoResponseDTO responseDTO = new TransacaoResponseDTO();
        responseDTO.setId(transacao.getId());
        responseDTO.setEmpresaId(empresa.getId());
        responseDTO.setClienteId(cliente.getId());
        responseDTO.setValor(transacao.getValor());
        responseDTO.setData(transacao.getData());
        responseDTO.setTipo("SAQUE");

        return ResponseEntity.ok(responseDTO);
    }

    // Métodos de exemplo para buscar Empresa e Cliente pelos serviços
    private Empresa buscarEmpresaPorId(Long empresaId) {
        // Aqui deve ser chamada a lógica real para buscar a empresa
        Empresa empresa = new Empresa(); // Exemplo; substituir com a chamada real
        empresa.setId(empresaId);
        return empresa;
    }

    private Cliente buscarClientePorId(Long clienteId) {
        // Aqui deve ser chamada a lógica real para buscar o cliente
        Cliente cliente = new Cliente(); // Exemplo; substituir com a chamada real
        cliente.setId(clienteId);
        return cliente;
    }
}