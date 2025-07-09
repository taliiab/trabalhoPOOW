package br.csi.controller;

import br.csi.model.Emprestimo;
import br.csi.model.Livro;
import br.csi.model.Usuario;
import br.csi.service.EmprestimoService;
import br.csi.service.LivroService;
import br.csi.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest; // Mantido para acesso direto a atributos e encoding
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; // Para facilitar a captura de parâmetros

import java.util.List;

@Controller // Marca esta classe como um Controller Spring MVC
@RequestMapping("/emprestimo") // Mapeamento base para todas as requisições deste controller
public class EmprestimoController {

    private static final EmprestimoService emprestimoService = new EmprestimoService();
    private static final UsuarioService usuarioService = new UsuarioService();
    private static final LivroService livroService = new LivroService();

    @PostMapping // Lida com todas as requisições POST para /emprestimo
    public String doPost(@RequestParam(name = "opcao", required = false) String opcao,
                         @RequestParam(name = "id_usuario", required = false) String idUsuarioStr,
                         @RequestParam(name = "id_livro", required = false) String idLivroStr,
                         @RequestParam(name = "id", required = false) String idStr,
                         HttpServletRequest req) { // Mantido para acesso direto ao request

        // Preserva a codificação de caracteres como no Servlet original
        try {
            req.setCharacterEncoding("UTF-8");
            // A codificação de resposta é geralmente tratada por filtros ou HttpMessageConverters do Spring
        } catch (Exception e) {
            System.err.println("Erro ao configurar a codificação de caracteres: " + e.getMessage());
        }

        Emprestimo emprestimo = new Emprestimo();

        try { // Bloco try-catch para NumberFormatException
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
            return forwardToEmprestimoPage(req, emprestimo); // Encaminha para a página com a mensagem de erro
        }


        String retorno = "";

        if (opcao != null) {
            switch (opcao.toLowerCase()) {
                case "cadastrar":
                    retorno = emprestimoService.cadastrar(emprestimo);
                    req.setAttribute("msg", retorno);
                    emprestimo = null; // Limpa o objeto após cadastro
                    break;

                case "excluir":
                    retorno = emprestimoService.excluir(emprestimo.getId());
                    req.setAttribute("msg", retorno);
                    emprestimo = null; // Limpa o objeto após exclusão
                    break;

                case "buscar":
                    Emprestimo e = emprestimoService.buscarPorId(emprestimo.getId());
                    req.setAttribute("emprestimo", e); // Define o empréstimo encontrado
                    // Não zera emprestimo aqui para que o form seja preenchido
                    break;

                case "atualizar":
                    retorno = emprestimoService.atualizar(emprestimo);
                    req.setAttribute("msg", retorno);
                    emprestimo = null; // Limpa o objeto após atualização
                    break;

                default:
                    req.setAttribute("msg", "Operação inválida.");
                    emprestimo = null; // Limpa o objeto para operação inválida
                    break;
            }
        } else {
            emprestimo = null; // Caso 'opcao' seja nula
        }

        // Esta parte da lógica garante que o objeto 'emprestimo' no request
        // seja o encontrado (se 'buscar') ou nulo/limpo para outras operações
        if (!"buscar".equalsIgnoreCase(opcao)) { // evita apagar empréstimo encontrado
            req.setAttribute("emprestimo", emprestimo); // Define para null ou o valor atual
        }

        // Coleta e define os atributos para a JSP
        return forwardToEmprestimoPage(req, (Emprestimo) req.getAttribute("emprestimo"));
    }

    @GetMapping // Lida com requisições GET para /emprestimo
    public String doGet(HttpServletRequest req) { // Mantido para acesso direto ao request

        // Preserva a codificação de caracteres como no Servlet original
        try {
            req.setCharacterEncoding("UTF-8");
            // A codificação de resposta é geralmente tratada por filtros ou HttpMessageConverters do Spring
        } catch (Exception e) {
            System.err.println("Erro ao configurar a codificação de caracteres: " + e.getMessage());
        }

        // Coleta e define os atributos para a JSP
        return forwardToEmprestimoPage(req, null); // Em GET, o formulário de empréstimo deve estar limpo
    }

    // Método auxiliar para consolidar a lógica de preparação de dados e encaminhamento da view
    private String forwardToEmprestimoPage(HttpServletRequest req, Emprestimo currentEmprestimo) {
        List<Usuario> usuarios = usuarioService.listar();
        List<Livro> livros = livroService.listar();
        List<Emprestimo> emprestimos = emprestimoService.listar();

        req.setAttribute("usuarios", usuarios);
        req.setAttribute("livros", livros);
        req.setAttribute("emprestimos", emprestimos);
        req.setAttribute("emprestimo", currentEmprestimo); // Define o empréstimo para o formulário (pode ser null)

        return "emprestimo"; // Retorna o caminho completo da JSP
    }
}