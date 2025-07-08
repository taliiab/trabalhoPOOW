package br.csi.service;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Livro;
import br.csi.model.Usuario;

import java.util.List;

public class UsuarioService {

    private static final UsuarioDAO dao = new UsuarioDAO();

    public boolean autenticar(Usuario usuario) {
        return dao.autenticar(usuario);
    }

    public String cadastrar(Usuario usuario) {
        return dao.cadastrar(usuario);
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public Usuario buscarDados(Usuario usuario) { return dao.buscarDados(usuario); }


    public String excluirUsuario(int id) {
        return dao.excluir(id);
    }

    public Usuario buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public String editarUsuario(Usuario usuario) {
        return dao.editar(usuario);
    }

}

