package com.sistema.transactionspring.domain;

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
    private double taxa;

    public Empresa() {
    }

    public Empresa(String cnpj, String nome, double saldo, double taxa) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.saldo = saldo;
        this.taxa = taxa;
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

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empresa empresa = (Empresa) o;
        return Double.compare(saldo, empresa.saldo) == 0 && Double.compare(taxa, empresa.taxa) == 0 && Objects.equals(cnpj, empresa.cnpj) && Objects.equals(nome, empresa.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, nome, saldo, taxa);
    }

}
