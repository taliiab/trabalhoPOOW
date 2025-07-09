package br.csi.controller;

import br.csi.model.Emprestimo;
import br.csi.model.Livro;
import br.csi.model.Usuario;
import br.csi.service.EmprestimoService;
import br.csi.service.LivroService;
import br.csi.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private static final EmprestimoService emprestimoService = new EmprestimoService();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final LivroService livroService = new LivroService();

    @PostMapping
    public String doPost(@RequestParam(name = "opcao", required = false) String opcao,
                         @RequestParam(name = "id_usuario", required = false) String idUsuarioStr,
                         @RequestParam(name = "id_livro", required = false) String idLivroStr,
                         @RequestParam(name = "id", required = false) String idStr,
                         HttpServletRequest req) {

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            System.err.println("Erro ao configurar a codificação de caracteres: " + e.getMessage());
        }

        Emprestimo emprestimo = new Emprestimo();

        try {
            if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
                emprestimo.setIdUsuario(Integer.parseInt(idUsuarioStr));
            }

            if (idLivroStr != null && !idLivroStr.isEmpty()) {
                emprestimo.setIdLivro(Integer.parseInt(idLivroStr));
            }

            if (idStr != null && !idStr.isEmpty()) {
                emprestimo.setId(Integer.parseInt(idStr));
            }
        } catch (NumberFormatException e) {
            req.setAttribute("msg", "Erro: ID de usuário, livro ou empréstimo inválido.");
            return forwardToEmprestimoPage(req, emprestimo);
        }


        String retorno = "";

        if (opcao != null) {
            switch (opcao.toLowerCase()) {
                case "cadastrar":
                    retorno = emprestimoService.cadastrar(emprestimo);
                    req.setAttribute("msg", retorno);
                    emprestimo = null;
                    break;

                case "excluir":
                    retorno = emprestimoService.excluir(emprestimo.getId());
                    req.setAttribute("msg", retorno);
                    emprestimo = null;
                    break;

                case "buscar":
                    Emprestimo e = emprestimoService.buscarPorId(emprestimo.getId());
                    req.setAttribute("emprestimo", e);
                    break;

                case "atualizar":
                    retorno = emprestimoService.atualizar(emprestimo);
                    req.setAttribute("msg", retorno);
                    emprestimo = null;
                    break;

                default:
                    req.setAttribute("msg", "Operação inválida.");
                    emprestimo = null;
                    break;
            }
        } else {
            emprestimo = null;
        }


        if (!"buscar".equalsIgnoreCase(opcao)) {
            req.setAttribute("emprestimo", emprestimo);
        }


        return forwardToEmprestimoPage(req, (Emprestimo) req.getAttribute("emprestimo"));
    }

    @GetMapping
    public String doGet(HttpServletRequest req) {

        try {
            req.setCharacterEncoding("UTF-8");
        } catch (Exception e) {
            System.err.println("Erro ao configurar a codificação de caracteres: " + e.getMessage());
        }

        return forwardToEmprestimoPage(req, null);
    }

    private String forwardToEmprestimoPage(HttpServletRequest req, Emprestimo currentEmprestimo) {
        List<Usuario> usuarios = usuarioService.listar();
        List<Livro> livros = livroService.listar();
        List<Emprestimo> emprestimos = emprestimoService.listar();

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("livros", livros);
        req.setAttribute("emprestimos", emprestimos);
        req.setAttribute("emprestimo", currentEmprestimo);

        return "emprestimo";
    }
}