package com.projetopix.ProjetoPix.controller;

import com.projetopix.ProjetoPix.entity.Transacao;
import com.projetopix.ProjetoPix.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/pix")
    @ResponseBody
    public Object transferirEListarTransacoes(@RequestParam String chave, @RequestParam Double valor) {
        boolean sucesso = transacaoService.transferir(chave, valor);

        if (!sucesso) {
            return Collections.singletonMap("erro", "Saldo ou limite insuficiente");
        }

        // Buscar as transações relacionadas à chave (CPF)
        List<Transacao> transacoes = transacaoService.pegarTransacoes(chave);

        // Retornar as transações em JSON
        return transacoes;
    }

}
