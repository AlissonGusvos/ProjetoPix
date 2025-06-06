package com.projetopix.ProjetoPix.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "contas_simples")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cpf;
    private Date data_criacao_conta;
    private double limite_transferencia;
    private double saldo;

    // GETTERS e SETTERS


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getData_criacao_conta() {
        return data_criacao_conta;
    }

    public double getLimite_transferencia() {
        return limite_transferencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setData_criacao_conta(Date data_criacao_conta) {
        this.data_criacao_conta = data_criacao_conta;
    }

    public void setLimite_transferencia(double limite_transferencia) {
        this.limite_transferencia = limite_transferencia;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
