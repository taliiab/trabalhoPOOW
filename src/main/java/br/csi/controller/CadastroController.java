package br.csi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastrar")
public class CadastroController {

    @GetMapping
    public String showCadastroForm() {
        return "cadastro";
    }
}