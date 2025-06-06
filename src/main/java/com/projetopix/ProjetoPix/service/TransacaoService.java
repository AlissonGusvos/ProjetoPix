package com.projetopix.ProjetoPix.service;

import com.projetopix.ProjetoPix.entity.Conta;
import com.projetopix.ProjetoPix.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta validarChave(String chave){
        return contaRepository.findByCpf(chave)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }


}
