package br.csi.controller;

import br.csi.dao.LivroDAO;
import br.csi.model.Livro;
import br.csi.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/elivro")
public class ELivroController {

    @PostMapping
    public String doPost(@RequestParam(name = "usuarioId", required = false) String paramId,
                         HttpServletRequest req,
                         HttpSession session) {

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            return "index";
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        String papel = (String) session.getAttribute("papel");

        int usuarioId;

        if ("admin".equals(papel) && paramId != null) {
            try {
                usuarioId = Integer.parseInt(paramId);
            } catch (NumberFormatException e) {
                req.setAttribute("msg", "ID de usuário inválido para admin.");
                usuarioId = usuarioLogado.getId();
            }
        } else {
            usuarioId = usuarioLogado.getId();
        }

        List<Livro> livros = new LivroDAO().listar();
        req.setAttribute("livros", livros);

        req.setAttribute("usuarioId", usuarioId);

        return "elivro";
    }
}