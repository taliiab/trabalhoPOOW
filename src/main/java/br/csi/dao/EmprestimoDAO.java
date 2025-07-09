package br.csi.dao;

import br.csi.model.Emprestimo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmprestimoDAO {

    public String cadastrar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (id_usuario, id_livro) VALUES (?, ?)";

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getIdUsuario());
            stmt.setInt(2, emprestimo.getIdLivro());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Empréstimo realizado com sucesso!";
            } else {
                return "Erro ao realizar empréstimo!";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao realizar empréstimo!";
        }
    }

    public boolean livroEmprestado(int idLivro) {
        String sql = "SELECT COUNT(*) AS total FROM emprestimo WHERE id_livro = ?";
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivro);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total") > 0;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }


    public List<Emprestimo> listar() {
        List<Emprestimo> emprestimos = new ArrayList<>();

        String sql = """
            SELECT e.id_emprestimo, e.id_usuario, e.id_livro,
                   u.nome AS nome_usuario,
                   l.titulo AS titulo_livro
            FROM emprestimo e
            INNER JOIN usuario u ON e.id_usuario = u.id_usuario
            INNER JOIN livro l ON e.id_livro = l.id_livro
            """;

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setId(rs.getInt("id_emprestimo"));
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setIdLivro(rs.getInt("id_livro"));
                e.setNomeUsuario(rs.getString("nome_usuario"));
                e.setTituloLivro(rs.getString("titulo_livro"));

                emprestimos.add(e);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }

    public String excluir(int id) {
        String sql = "DELETE FROM emprestimo WHERE id_emprestimo = ?";

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Empréstimo excluído com sucesso!";
            } else {
                return "Empréstimo não encontrado para exclusão!";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao excluir empréstimo!";
        }
    }

    public Emprestimo buscarPorId(int id) {
        Emprestimo emprestimo = null;
        String sql = "SELECT * FROM emprestimo WHERE id_emprestimo = ?";

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    emprestimo = new Emprestimo();
                    emprestimo.setId(rs.getInt("id_emprestimo"));
                    emprestimo.setIdUsuario(rs.getInt("id_usuario"));
                    emprestimo.setIdLivro(rs.getInt("id_livro"));
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return emprestimo;
    }

    public String atualizar(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET id_usuario = ?, id_livro = ? WHERE id_emprestimo = ?";

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getIdUsuario());
            stmt.setInt(2, emprestimo.getIdLivro());
            stmt.setInt(3, emprestimo.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Empréstimo atualizado com sucesso!";
            } else {
                return "Empréstimo não encontrado para atualização!";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao atualizar empréstimo!";
        }
    }
}
