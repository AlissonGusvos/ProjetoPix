package com.projetopix.ProjetoPix.controller;

import com.projetopix.ProjetoPix.entity.Transacao;
import com.projetopix.ProjetoPix.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/pix")
    public String pix(Model model){
        return "pix";
    }

    /*@PostMapping("/pix")
    public String transacao(@RequestParam String chave, @RequestParam Double valor){
        transacaoService.transferir(chave, valor);
        return "redirect:/";
    }*/

    private List<Transacao> ultimasTransacoes;

    @PostMapping("/pix")
    public String transacao(@RequestParam String chave, @RequestParam Double valor) {
        ultimasTransacoes = transacaoService.pegarTransacoes(chave);

        // Verifica se existem valores semelhantes no histórico
        if (transacaoService.validarTransferencia(ultimasTransacoes)) {
            // Aqui já bloqueia a transferência, retornando para a página com erro
            return "redirect:/pix?erro=valoresSemelhantes";
        }

        boolean sucesso = transacaoService.transferir(chave, valor);

        if (sucesso) {
            ultimasTransacoes = transacaoService.pegarTransacoes(chave);
        } else {
            return "redirect:/pix?erro=transferenciaSuspeita";
        }

        return "redirect:/";
    }

    // GETTER DA LISTA
    public List<Transacao> getUltimasTransacoes() {
        return ultimasTransacoes;
    }

    // ROTA PARA TESTE DE LISTA DE TRANSAÇÕES
    /*
    @GetMapping("/transacoes-recentes")
    @ResponseBody
    public List<Transacao> transacoesRecentes() {
        return ultimasTransacoes != null ? ultimasTransacoes : new ArrayList<>();
    }
     */

}
