package com.sistema.tgiddesafio.controller;

import com.sistema.tgiddesafio.domain.Cliente;
import com.sistema.tgiddesafio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity <Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping ("/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
        clienteService.buscarClientePorCpf(cpf);
        if (clienteService.buscarClientePorCpf(cpf) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteService.buscarClientePorCpf(cpf));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.ok().build();
    }
}
