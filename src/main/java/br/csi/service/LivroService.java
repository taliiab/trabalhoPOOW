package br.csi.service;

import br.csi.dao.LivroDAO;
import br.csi.model.Livro;

import java.util.List;

public class LivroService {

    private static final LivroDAO dao = new LivroDAO();

    public String cadastrar(Livro livro) {
        return dao.cadastrar(livro);
    }

    public List<Livro> listar() {
        return dao.listar();
    }

    public String excluir(int id) {
        return dao.excluir(id);
    }

    public Livro buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public String atualizar(Livro livro) {
        return dao.atualizar(livro);
    }
}
