package com.projetopix.ProjetoPix.controller;

import com.projetopix.ProjetoPix.entity.Conta;
import com.projetopix.ProjetoPix.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping("/")
    public String home(Model model){
        Conta infoMainUser = contaService.mainUser();
        model.addAttribute("user",infoMainUser);

        return "index";
    }


}
