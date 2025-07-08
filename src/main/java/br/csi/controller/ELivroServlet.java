package br.csi.controller;

import br.csi.dao.LivroDAO;
import br.csi.model.Livro;
import br.csi.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/elivro")
public class ELivroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("usuarioLogado") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        String papel = (String) session.getAttribute("papel");

        int usuarioId;

        String paramId = req.getParameter("usuarioId");
        if ("admin".equals(papel) && paramId != null) {
            usuarioId = Integer.parseInt(paramId);
        } else {
            usuarioId = usuarioLogado.getId();
        }

        List<Livro> livros = new LivroDAO().listar();
        req.setAttribute("livros", livros);

        req.setAttribute("usuarioId", usuarioId);

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/elivro.jsp");
        rd.forward(req, resp);
    }




}
