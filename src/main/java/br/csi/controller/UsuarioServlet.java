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
import java.util.List;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    /*private static final UsuarioService service = new UsuarioService();

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
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/cadastro.jsp");
            rd.forward(req, resp);

        } else if ("login".equalsIgnoreCase(opcao)) {
            boolean autenticar = service.autenticar(usuario);
            boolean usuarioFixo = "admin@admin.com".equals(email) && "admin".equals(senha);

            if (autenticar || usuarioFixo) {
                HttpSession session = req.getSession();
                if (usuarioFixo) {
                    usuario = new Usuario();
                    usuario.setNome("admin");
                    usuario.setEmail("admin@admin.com");
                    usuario.setSenha("admin");
                    session.setAttribute("papel", "admin");
                } else {
                    usuario = service.buscarDados(usuario);
                    session.setAttribute("papel", "usuario");
                }

                session.setAttribute("usuarioLogado", usuario);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/paginicial.jsp");
                rd.forward(req, resp);
            } else {
                req.setAttribute("msg", "Login ou senha incorretos!");
                RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
                rd.forward(req, resp);
            }

        } else if ("excluir".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(req.getParameter("id"));
            retorno = service.excluirUsuario(id);
            req.setAttribute("msg", retorno);
            req.setAttribute("usuarios", service.listar());
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/cadastro.jsp");
            rd.forward(req, resp);

        } else if ("buscar".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Usuario u = service.buscarPorId(id);
            req.setAttribute("usuario", u);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/cadastro.jsp");
            rd.forward(req, resp);

        } else if ("editar".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(req.getParameter("id"));
            usuario.setId(id);
            retorno = service.editarUsuario(usuario);
            req.setAttribute("msg", retorno);
            req.setAttribute("usuarios", service.listar());
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/cadastro.jsp");
            rd.forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Usuario> usuarios = service.listar();
        req.setAttribute("usuarios", usuarios);

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/pages/cadastro.jsp");
        rd.forward(req, resp);
    } */

}
