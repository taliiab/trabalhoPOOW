package br.csi.controller;

import br.csi.model.Emprestimo;
import br.csi.model.Livro;
import br.csi.model.Usuario;
import br.csi.service.EmprestimoService;
import br.csi.service.LivroService;
import br.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/emprestimo")
public class EmprestimoServlet extends HttpServlet {

    /*private static final EmprestimoService emprestimoService = new EmprestimoService();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final LivroService livroService = new LivroService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String opcao = req.getParameter("opcao");

        String idUsuarioStr = req.getParameter("id_usuario");
        String idLivroStr = req.getParameter("id_livro");
        String idStr = req.getParameter("id");

        Emprestimo emprestimo = new Emprestimo();

        if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
            emprestimo.setIdUsuario(Integer.parseInt(idUsuarioStr));
        }

        if (idLivroStr != null && !idLivroStr.isEmpty()) {
            emprestimo.setIdLivro(Integer.parseInt(idLivroStr));
        }

        if (idStr != null && !idStr.isEmpty()) {
            emprestimo.setId(Integer.parseInt(idStr));
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


        if (!"buscar".equalsIgnoreCase(opcao)) { //evita apagar empréstimo encontrado
            req.setAttribute("emprestimo", emprestimo);
        }

        List<Usuario> usuarios = usuarioService.listar();
        List<Livro> livros = livroService.listar();
        List<Emprestimo> emprestimos = emprestimoService.listar();

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("livros", livros);
        req.setAttribute("emprestimos", emprestimos);

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/emprestimo.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        List<Usuario> usuarios = usuarioService.listar();
        List<Livro> livros = livroService.listar();
        List<Emprestimo> emprestimos = emprestimoService.listar();

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("livros", livros);
        req.setAttribute("emprestimos", emprestimos);
        req.setAttribute("emprestimo", null); //form limpo

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/emprestimo.jsp");
        rd.forward(req, resp);
    } */
}
