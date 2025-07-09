package br.csi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Marca esta classe como um Controller Spring MVC
@RequestMapping("/cadastrar") // Mapeamento base para todos os métodos neste controller
public class CadastroController {

    @GetMapping // Lida com requisições GET para /cadastrar
    public String showCadastroForm() {
        // A String "cadastro" será resolvida pelo ViewResolver do Spring
        // para, tipicamente, procurar por um arquivo JSP como /WEB-INF/pages/cadastro.jsp
        return "cadastro";
    }
}