package com.sistema.transacoes.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "transacoes")

public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private LocalDateTime data;
    private String tipo;

    @ManyToOne
    @JoinColumn (name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @JoinColumn (name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Transacao() {
    }

    public Transacao(Empresa empresa, Cliente cliente, double valorFinal, LocalDateTime now, String deposito) {
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
