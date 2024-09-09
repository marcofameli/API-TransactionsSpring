package com.sistema.tgid.service;

import com.sistema.tgid.domain.Cliente;
import com.sistema.tgid.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired

    private ClienteRepository clienteRepository;

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public void atualizarSaldo(Long idCliente, double valor) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));

        double novoSaldo = cliente.getSaldo() + valor;
        cliente.setSaldo(novoSaldo);
        clienteRepository.save(cliente);

    }
}
