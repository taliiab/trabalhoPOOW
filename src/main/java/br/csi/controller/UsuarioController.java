package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest; // Mantemos para acesso direto à sessão e atributos
import jakarta.servlet.http.HttpSession; // Mantemos para acesso direto à sessão
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Opcional, mas útil para adicionar atributos
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // Para facilitar a captura de parâmetros
import org.springframework.web.servlet.mvc.support.RedirectAttributes; // Para flash attributes se necessário, mas não vamos usar agora para manter a lógica original

import java.util.List;

@Controller
@RequestMapping("/usuario") // Mapeamento base para todas as requisições deste controller
public class UsuarioController {

    private final UsuarioService service = new UsuarioService(); // Mantemos a instância do service

    @PostMapping // Lida com todas as requisições POST para /usuario
    public String doPost(@RequestParam(name = "opcao") String opcao, // Captura o parâmetro 'opcao'
                         @RequestParam(name = "nome", required = false) String nome, // Captura 'nome' (pode ser nulo)
                         @RequestParam(name = "email", required = false) String email, // Captura 'email' (pode ser nulo)
                         @RequestParam(name = "senha", required = false) String senha, // Captura 'senha' (pode ser nulo)
                         @RequestParam(name = "id", required = false) String idParam, // Captura 'id' como String para parsear
                         HttpServletRequest req, // Mantemos para acesso direto a atributos e sessão
                         HttpSession session) { // Mantemos para acesso direto à sessão

        String retorno;
        Usuario usuario = new Usuario();

        // Popula o objeto usuario com base nos parâmetros recebidos
        // Observação: No Spring "real", usaríamos @ModelAttribute para fazer isso automaticamente
        // Mas para manter a lógica original, faremos manualmente aqui.
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        if (idParam != null && !idParam.isEmpty()) {
            usuario.setId(Integer.parseInt(idParam));
        }


        if ("cadastrar".equalsIgnoreCase(opcao)) {
            retorno = service.cadastrar(usuario);
            req.setAttribute("msg", retorno); // Mantém o atributo no request
            return "cadastro"; // Retorna o caminho completo da JSP

        } else if ("login".equalsIgnoreCase(opcao)) {
            boolean autenticar = service.autenticar(usuario);
            boolean usuarioFixo = "admin@admin.com".equals(email) && "admin".equals(senha);

            if (autenticar || usuarioFixo) {
                // HttpSession session = req.getSession(); // Já recebemos a sessão como parâmetro

                if (usuarioFixo) {
                    usuario = new Usuario(); // Cria um novo objeto para o admin fixo
                    usuario.setNome("admin");
                    usuario.setEmail("admin@admin.com");
                    usuario.setSenha("admin");
                    session.setAttribute("papel", "admin");
                } else {
                    usuario = service.buscarDados(usuario);
                    session.setAttribute("papel", "usuario");
                }

                session.setAttribute("usuarioLogado", usuario);
                return "paginicial"; // Retorna o caminho completo da JSP
            } else {
                req.setAttribute("msg", "Login ou senha incorretos!"); // Mantém o atributo no request
                return "index"; // Retorna o caminho completo da JSP
            }

        } else if ("excluir".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(idParam); // O idParam já vem do @RequestParam
            retorno = service.excluirUsuario(id);
            req.setAttribute("msg", retorno); // Mantém o atributo no request
            req.setAttribute("usuarios", service.listar()); // Mantém o atributo no request
            return "cadastro"; // Retorna o caminho completo da JSP

        } else if ("buscar".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(idParam); // O idParam já vem do @RequestParam
            Usuario u = service.buscarPorId(id);
            req.setAttribute("usuario", u); // Mantém o atributo no request
            return "cadastro"; // Retorna o caminho completo da JSP

        } else if ("editar".equalsIgnoreCase(opcao)) {
            int id = Integer.parseInt(idParam); // O idParam já vem do @RequestParam
            usuario.setId(id);
            retorno = service.editarUsuario(usuario);
            req.setAttribute("msg", retorno); // Mantém o atributo no request
            req.setAttribute("usuarios", service.listar()); // Mantém o atributo no request
            return "cadastro"; // Retorna o caminho completo da JSP
        }
        // Se nenhuma opção corresponder, talvez redirecionar para uma página de erro ou a própria página de cadastro
        req.setAttribute("msg", "Operação inválida!");
        return "cadastro";
    }

    @GetMapping // Lida com todas as requisições GET para /usuario
    public String doGet(HttpServletRequest req) { // Mantemos HttpServletRequest para passar atributos

        List<Usuario> usuarios = service.listar();
        req.setAttribute("usuarios", usuarios); // Mantém o atributo no request

        return "cadastro"; // Retorna o caminho completo da JSP
    }
}