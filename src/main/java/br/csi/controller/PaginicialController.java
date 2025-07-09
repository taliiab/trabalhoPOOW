package br.csi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Marca esta classe como um Controller Spring MVC
@RequestMapping("/paginicial") // Mapeamento base para todas as requisições deste controller
public class PaginicialController {

    @GetMapping // Lida com requisições GET para /paginicial
    public String showPaginaInicial() {
        // Retorna o nome lógico da view, que o ViewResolver resolverá para o caminho físico
        // Ex: /WEB-INF/pages/paginicial.jsp
        return "paginicial";
    }
}