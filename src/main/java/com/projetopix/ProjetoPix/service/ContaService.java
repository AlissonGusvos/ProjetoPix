package com.projetopix.ProjetoPix.service;

import com.projetopix.ProjetoPix.entity.Conta;
import com.projetopix.ProjetoPix.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta mainUser(){
        return contaRepository.findById(31L)
                .orElseThrow(() -> new RuntimeException("USUÁRIO PRINCIPAL NÃO ENCONTRADO"));
    }
}
