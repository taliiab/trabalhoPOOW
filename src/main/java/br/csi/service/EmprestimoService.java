package br.csi.service;

import br.csi.dao.EmprestimoDAO;
import br.csi.model.Emprestimo;
import br.csi.model.Livro;

import java.util.List;

public class EmprestimoService {

    private static final EmprestimoDAO dao = new EmprestimoDAO();

    public String cadastrar(Emprestimo emprestimo) {
        if (emprestimo == null || emprestimo.getIdUsuario() <= 0 || emprestimo.getIdLivro() <= 0) {
            return "Dados do empréstimo inválidos.";
        }

        if (dao.livroEmprestado(emprestimo.getIdLivro())) {
            return "O livro já está emprestado!!";
        }

        return dao.cadastrar(emprestimo);
    }


    public List<Emprestimo> listar() {
        return dao.listar();
    }

    public String excluir(int id) {
        if (id <= 0) {
            return "ID inválido para exclusão.";
        }
        return dao.excluir(id);
    }

    public Emprestimo buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return dao.buscarPorId(id);
    }

    public String atualizar(Emprestimo emprestimo) {
        if (emprestimo == null || emprestimo.getId() <= 0 || emprestimo.getIdUsuario() <= 0 || emprestimo.getIdLivro() <= 0) {
            return "Dados do empréstimo inválidos para atualização.";
        }
        return dao.atualizar(emprestimo);
    }

}
