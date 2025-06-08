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

}

