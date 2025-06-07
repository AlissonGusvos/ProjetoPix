package com.projetopix.ProjetoPix.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transacoes_bancarias")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_conta_origem")
    private Long idContaOrigem;

    @Column(name = "id_conta_destino")
    private Long idContaDestino;

    private double valor;

    // GETTERS e SETTERS

    public Long getId() {
        return id;
    }

    public Long getIdContaOrigem() {
        return idContaOrigem;
    }

    public Long getIdContaDestino() {
        return idContaDestino;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setIdContaDestino(Long idContaDestino) {
        this.idContaDestino = idContaDestino;
    }

    public void setIdContaOrigem(Long idContaOrigem) {
        this.idContaOrigem = idContaOrigem;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

