package br.csi.controller;

import br.csi.model.Livro;
import br.csi.service.LivroService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/livro")
public class LivroController {

    private static final LivroService service = new LivroService();

    @PostMapping
    public String doPost(@RequestParam(name = "opcao") String opcao,
                         @RequestParam(name = "titulo", required = false) String titulo,
                         @RequestParam(name = "autor", required = false) String autor,
                         @RequestParam(name = "editora", required = false) String editora,
                         @RequestParam(name = "ano", required = false) String anoStr,
                         @RequestParam(name = "id", required = false) String idStr,
                         HttpServletRequest req) {


        try {
            req.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            System.err.println("Error setting character encoding: " + e.getMessage());
        }

        System.out.println("opcao: " + opcao);

        Livro livro = new Livro();
        if (titulo != null) livro.setTitulo(titulo);
        if (autor != null) livro.setAutor(autor);
        if (editora != null) livro.setEditora(editora);
        if (anoStr != null && !anoStr.isEmpty()) {
            try {
                livro.setAno(Integer.parseInt(anoStr));
            } catch (NumberFormatException e) {
                req.setAttribute("msg", "Ano inválido.");
                return forwardList(req);
            }
        }
        if (idStr != null && !idStr.isEmpty()) {
            try {
                livro.setId(Integer.parseInt(idStr));
            } catch (NumberFormatException e) {
                req.setAttribute("msg", "ID inválido.");
                return forwardList(req);
            }
        }

        String retorno = "";

        if ("cadastrar".equalsIgnoreCase(opcao)) {
            retorno = service.cadastrar(livro);
            req.setAttribute("msg", retorno);

        } else if ("excluir".equalsIgnoreCase(opcao)) {
            retorno = service.excluir(livro.getId());
            req.setAttribute("msg", retorno);

        } else if ("buscar".equalsIgnoreCase(opcao)) {
            Livro l = service.buscarPorId(livro.getId());
            req.setAttribute("livro", l);
        } else if ("atualizar".equalsIgnoreCase(opcao)) {
            retorno = service.atualizar(livro);
            req.setAttribute("msg", retorno);
        } else {
            req.setAttribute("msg", "Operação inválida.");
        }

        if (!"buscar".equalsIgnoreCase(opcao)) {
            return forwardList(req);
        } else {
            return "livro";
        }
    }

    private String forwardList(HttpServletRequest req) {
        List<Livro> livros = service.listar();
        req.setAttribute("livros", livros);
        return "livro";
    }

    @GetMapping
    public String doGet(HttpServletRequest req) {
        return forwardList(req);
    }
}