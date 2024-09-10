package com.sistema.tgiddesafio.domain;

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

    public Transacao(double valor, LocalDateTime data, String tipo, Empresa empresa, Cliente cliente) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.empresa = empresa;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
