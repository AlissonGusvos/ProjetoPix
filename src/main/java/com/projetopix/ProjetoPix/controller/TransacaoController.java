package com.projetopix.ProjetoPix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransacaoController {
    @GetMapping("/pix")
    public String pix(Model model){
        return "pix";
    }
}
