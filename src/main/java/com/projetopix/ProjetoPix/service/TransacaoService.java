package com.projetopix.ProjetoPix.service;

import com.projetopix.ProjetoPix.entity.Conta;
import com.projetopix.ProjetoPix.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaService contaService;

    public Conta validarChave(String chave){
        return contaRepository.findByCpf(chave)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public boolean transferir(String chave, Double valor){
        Conta mainUser = contaService.mainUser();
        Conta contaDestino = validarChave(chave);

        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        contaRepository.save(contaDestino);

        mainUser.setSaldo(mainUser.getSaldo() - valor);
        contaRepository.save(mainUser);

        return true;
    }


}
