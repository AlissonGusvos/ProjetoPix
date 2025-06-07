package com.projetopix.ProjetoPix.service;

import com.projetopix.ProjetoPix.entity.Conta;
import com.projetopix.ProjetoPix.entity.Transacao;
import com.projetopix.ProjetoPix.repository.ContaRepository;
import com.projetopix.ProjetoPix.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ContaService contaService;
    @Autowired
    private TransacaoRepository transacaoRepository;

    public Conta validarChave(String chave){
        return contaRepository.findByCpf(chave)
                .orElseThrow(() -> new RuntimeException("CONTA NÃO ENCONTRADA"));
    }

    public List<Transacao> pegarTransacoes(String cpf) {
        Conta contaDestino = validarChave(cpf);
        return transacaoRepository.findByIdContaDestino(contaDestino.getId());
    }


    public boolean transferir(String chave, Double valor){
        Conta mainUser = contaService.mainUser();
        Conta contaDestino = validarChave(chave);

        if(valor > mainUser.getLimite_transferencia()){
            return false;
        }
        else if(valor > mainUser.getSaldo()){
            return false;
        }

        contaDestino.setSaldo(contaDestino.getSaldo() + valor);
        contaRepository.save(contaDestino);

        mainUser.setSaldo(mainUser.getSaldo() - valor);
        contaRepository.save(mainUser);

        return true;
    }

    public boolean validarTransferencia(List<Transacao> transacoes) {
        final double tolerancia = 3;

        for (int i = 0; i < transacoes.size(); i++) {
            double valorI = transacoes.get(i).getValor();

            for (int j = i + 1; j < transacoes.size(); j++) {
                double valorJ = transacoes.get(j).getValor();

                if (Math.abs(valorI - valorJ) <= tolerancia) {
                    return true; // Achou valores semelhantes
                }
            }
        }
        return false;
    }


}
