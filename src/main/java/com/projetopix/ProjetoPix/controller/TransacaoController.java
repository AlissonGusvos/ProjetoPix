package com.projetopix.ProjetoPix.controller;

import com.projetopix.ProjetoPix.entity.Conta;
import com.projetopix.ProjetoPix.entity.Transacao;
import com.projetopix.ProjetoPix.service.ContaService;
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

    @Autowired
    private ContaService contaService;

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
    public String transacao(@RequestParam String chave,
                            @RequestParam Double valor,
                            @RequestParam(required = false) Boolean confirmar) {

        // Se ainda não confirmou e os valores são suspeitos
        if (confirmar == null || !confirmar) {
            List<Transacao> ultimasTransacoes = transacaoService.pegarTransacoes(chave);

            if (transacaoService.validarTransferencia(ultimasTransacoes)) {
                return "redirect:/pix?erro=valoresSemelhantes&chave=" + chave + "&valor=" + valor;
            }
        }

        boolean sucesso = Boolean.parseBoolean(transacaoService.transferir(chave, valor));

        if (!sucesso) {
            Conta mainUser = contaService.mainUser();
            if (valor > mainUser.getSaldo()) {
                return "redirect:/pix?erro=saldoInsuficiente";
            } else if (valor > mainUser.getLimite_transferencia()) {
                return "redirect:/pix?erro=limiteExcedido";
            }
            return "redirect:/pix?erro=transferenciaFalhou";
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
