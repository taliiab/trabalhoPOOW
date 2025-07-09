package br.csi.controller;

import br.csi.model.Livro;
import br.csi.service.LivroService;
import jakarta.servlet.http.HttpServletRequest; // Kept for direct request attribute and encoding access
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // For capturing parameters easily

import java.util.List;

@Controller // Marks this class as a Spring MVC Controller
@RequestMapping("/livro") // Base mapping for all requests to this controller
public class LivroController {

    private static final LivroService service = new LivroService(); // Maintains the service instance

    @PostMapping // Handles all POST requests to /livro
    public String doPost(@RequestParam(name = "opcao") String opcao,
                         @RequestParam(name = "titulo", required = false) String titulo,
                         @RequestParam(name = "autor", required = false) String autor,
                         @RequestParam(name = "editora", required = false) String editora,
                         @RequestParam(name = "ano", required = false) String anoStr,
                         @RequestParam(name = "id", required = false) String idStr,
                         HttpServletRequest req) { // Kept for direct HttpServletRequest access

        // Preserve character encoding as in the original Servlet
        try {
            req.setCharacterEncoding("UTF-8");
            // resp.setCharacterEncoding("UTF-8"); // Handled by Spring's HttpMessageConverter or filters
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
                return forwardList(req); // Call the helper method and return its result
            }
        }
        if (idStr != null && !idStr.isEmpty()) {
            try {
                livro.setId(Integer.parseInt(idStr));
            } catch (NumberFormatException e) {
                req.setAttribute("msg", "ID inválido.");
                return forwardList(req); // Call the helper method and return its result
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
            return forwardList(req); // Call the helper method and return its result
        } else {
            return "livro"; // Return the full JSP path
        }
    }

    // Helper method to consolidate logic for forwarding to the list view
    private String forwardList(HttpServletRequest req) {
        List<Livro> livros = service.listar();
        req.setAttribute("livros", livros);
        return "livro"; // Return the full JSP path
    }

    @GetMapping // Handles all GET requests to /livro
    public String doGet(HttpServletRequest req) { // Kept HttpServletRequest for attribute passing
        return forwardList(req); // Call the helper method and return its result
    }
}