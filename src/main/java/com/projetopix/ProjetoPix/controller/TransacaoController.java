package com.projetopix.ProjetoPix.controller;

import com.projetopix.ProjetoPix.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/pix")
    public String pix(Model model){
        return "pix";
    }

    @PostMapping("/pix")
    public String transacao(@RequestParam String chave, @RequestParam Double valor){
        transacaoService.transferir(chave, valor);

        return "redirect:/";
    }
}
