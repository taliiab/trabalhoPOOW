package br.csi.dao;

import br.csi.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public String cadastrar(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)"
            );

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao cadastrar usuario!");

            return "Erro ao cadastrar usuário!";
        }

        return "Usuário cadastrado com sucesso!";
    }

    public boolean autenticar(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao autenticar usuário!");
        }

        return false;
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT id_usuario, nome, email FROM usuario";

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                usuarios.add(u);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
}
