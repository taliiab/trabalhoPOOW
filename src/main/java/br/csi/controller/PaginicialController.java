package br.csi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paginicial")
public class PaginicialController {

    @GetMapping
    public String showPaginaInicial() {
        return "paginicial";
    }
}