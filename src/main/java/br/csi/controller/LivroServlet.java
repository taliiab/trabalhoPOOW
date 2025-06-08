package br.csi.controller;

import br.csi.model.Livro;
import br.csi.service.LivroService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/livro")
public class LivroServlet extends HttpServlet {

    private static final LivroService service = new LivroService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String opcao = req.getParameter("opcao");

        String titulo = req.getParameter("titulo");
        String autor = req.getParameter("autor");
        String editora = req.getParameter("editora");
        String anoStr = req.getParameter("ano");
        String idStr = req.getParameter("id");

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
                forwardList(req, resp);
                return;
            }
        }
        if (idStr != null && !idStr.isEmpty()) {
            try {
                livro.setId(Integer.parseInt(idStr));
            } catch (NumberFormatException e) {
                req.setAttribute("msg", "ID inválido.");
                forwardList(req, resp);
                return;
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
            forwardList(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/livro.jsp");
            rd.forward(req, resp);
        }
    }

    private void forwardList(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Livro> livros = service.listar();
        req.setAttribute("livros", livros);

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/livro.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        forwardList(req, resp);
    }
}
