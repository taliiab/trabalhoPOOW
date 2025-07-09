package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service = new UsuarioService();

    @PostMapping
    public String doPost(@RequestParam(name = "opcao") String opcao,
                         @RequestParam(name = "nome", required = false) String nome,
                         @RequestParam(name = "email", required = false) String email,
                         @RequestParam(name = "senha", required = false) String senha,
                         @RequestParam(name = "id", required = false) String idParam,
                         HttpServletRequest req,
                         HttpSession session) {

        String retorno;
        Usuario usuario = new Usuario();


        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        if (idParam != null && !idParam.isEmpty()) {
            usuario.setId(Integer.parseInt(idParam));
        }


        if ("cadastrar".equalsIgnoreCase(opcao)) {
            retorno = service.cadastrar(usuario);
            req.setAttribute("msg", retorno);
            return "cadastro";

        } else if ("login".equalsIgnoreCase(opcao)) {
            boolean autenticar = service.autenticar(usuario);
            boolean usuarioFixo = "admin@admin.com".equals(email) && "admin".equals(senha);

            if (autenticar || usuarioFixo) {

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
                return "paginicial";
            } else {
                req.setAttribute("msg", "Login ou senha incorretos!");
                return "index";
            }

        } else if ("excluir".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(idParam);
            retorno = service.excluirUsuario(id);
            req.setAttribute("msg", retorno);
            req.setAttribute("usuarios", service.listar());
            return "cadastro";

        } else if ("buscar".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(idParam);
            Usuario u = service.buscarPorId(id);
            req.setAttribute("usuario", u);
            return "cadastro";

        } else if ("editar".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(idParam);
            usuario.setId(id);
            retorno = service.editarUsuario(usuario);
            req.setAttribute("msg", retorno);
            req.setAttribute("usuarios", service.listar());
            return "cadastro";
        }
        req.setAttribute("msg", "Operação inválida!");
        return "cadastro";
    }

    @GetMapping
    public String doGet(HttpServletRequest req) {

        List<Usuario> usuarios = service.listar();
        req.setAttribute("usuarios", usuarios);

        return "cadastro";
    }
}