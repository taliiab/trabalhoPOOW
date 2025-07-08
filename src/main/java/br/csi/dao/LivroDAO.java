package br.csi.dao;

import br.csi.model.Livro;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public String cadastrar(Livro livro) {
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO livro (titulo, autor, editora, ano) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Livro cadastrado com sucesso!";
            } else {
                return "Erro ao cadastrar livro.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao cadastrar livro: " + e.getMessage();
        }
    }

    public List<Livro> listar() {
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM livro")) {

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAno(rs.getInt("ano"));

                String verificaEmprestimo = "SELECT COUNT(*) FROM emprestimo WHERE id_livro = ?";
                PreparedStatement stmt2 = conn.prepareStatement(verificaEmprestimo);
                stmt2.setInt(1, livro.getId());

                ResultSet rs2 = stmt2.executeQuery();
                rs2.next();

                int totalEmprestimos = rs2.getInt(1);
                livro.setDisponivel(totalEmprestimos == 0);

                livros.add(livro);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public String excluir(int id) {
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM livro WHERE id_livro = ?")) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Livro excluído com sucesso!";
            } else {
                return "Livro não encontrado para exclusão.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao excluir livro: " + e.getMessage();
        }
    }

    public Livro buscarPorId(int id) {
        Livro livro = null;

        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM livro WHERE id_livro = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                livro = new Livro();
                livro.setId(rs.getInt("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAno(rs.getInt("ano"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return livro;
    }

    public String atualizar(Livro livro) {
        try (Connection conn = ConectarBancoDados.conectarBancoPostgres();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE livro SET titulo = ?, autor = ?, editora = ?, ano = ? WHERE id_livro = ?")) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setInt(5, livro.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Livro atualizado com sucesso!";
            } else {
                return "Livro não encontrado para atualizar.";
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return "Erro ao atualizar livro: " + e.getMessage();
        }
    }
}
