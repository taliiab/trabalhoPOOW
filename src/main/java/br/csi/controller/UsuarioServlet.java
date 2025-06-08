package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private static final UsuarioService service = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        String retorno;

        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);

        if ("cadastrar".equalsIgnoreCase(opcao)) {
            retorno = service.cadastrar(usuario);
            req.setAttribute("msg", retorno);
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);

        }else if ("login".equalsIgnoreCase(opcao)) {
            boolean autenticar = service.autenticar(usuario);

            if (autenticar) {
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", usuario);

                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/paginicial.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("msg", "Login ou senha incorretos!");
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }

        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
