package br.csi.controller;

import br.csi.dao.LivroDAO;
import br.csi.model.Livro;
import br.csi.model.Usuario;
import jakarta.servlet.http.HttpServletRequest; // Mantido para acesso direto ao request e atributos
import jakarta.servlet.http.HttpSession; // Mantido para acesso direto à sessão
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller // Marca esta classe como um Controller Spring MVC
@RequestMapping("/elivro") // Mapeamento base para todas as requisições deste controller
public class ELivroController {

    @PostMapping // Lida com requisições POST para /elivro
    public String doPost(@RequestParam(name = "usuarioId", required = false) String paramId,
                         HttpServletRequest req,
                         HttpSession session) { // Spring pode injetar a HttpSession diretamente

        // Lógica de verificação de sessão exatamente como no Servlet original
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            return "index"; // Redireciona para index.jsp
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        String papel = (String) session.getAttribute("papel");

        int usuarioId;

        // Lógica para determinar o ID do usuário exatamente como no Servlet original
        if ("admin".equals(papel) && paramId != null) {
            try {
                usuarioId = Integer.parseInt(paramId);
            } catch (NumberFormatException e) {
                // Se o ID for inválido, podemos definir uma mensagem de erro
                req.setAttribute("msg", "ID de usuário inválido para admin.");
                usuarioId = usuarioLogado.getId(); // Volta para o ID do usuário logado
            }
        } else {
            usuarioId = usuarioLogado.getId();
        }

        List<Livro> livros = new LivroDAO().listar();
        req.setAttribute("livros", livros); // Mantém o atributo no request

        req.setAttribute("usuarioId", usuarioId); // Mantém o atributo no request

        // Retorna o caminho completo da JSP, como era feito com RequestDispatcher
        return "elivro";
    }
}