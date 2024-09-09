package com.sistema.transacoes.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empresas")

public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cnpj;

    private String nome;
    private double saldo;

    public Empresa() {
    }

    public Empresa(String cnpj, String nome, double saldo) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", saldo=" + saldo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empresa empresa = (Empresa) o;
        return Objects.equals(id, empresa.id) && Objects.equals(cnpj, empresa.cnpj) && Objects.equals(nome, empresa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj);
    }
}
