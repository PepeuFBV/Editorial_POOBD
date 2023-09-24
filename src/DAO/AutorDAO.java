package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.entities.Autor;

public class AutorDAO extends UsersDAO {
    
    public void inserir(Autor autor) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO autor (nome, login, senha, endereco, cpf) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getLogin());
            statement.setString(3, autor.getSenha());
            statement.setString(4, autor.getEndereco());
            statement.setString(5, autor.getCpf());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                autor.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }
            statement.close();
            BaseDAOImpl.closeConnection();
            super.inserir(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Autor autor) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE autor SET nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id_autor = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getLogin());
            statement.setString(3, autor.getSenha());
            statement.setString(4, autor.getEndereco());
            statement.setString(5, autor.getCpf());
            statement.setInt(6, autor.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
            super.atualizar(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet buscarPorId(Autor autor) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM autor WHERE id_autor = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, autor.getId());
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    public ResultSet buscarPorNome(Autor autor) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM autor WHERE nome LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + autor.getNome() + "%");
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet listarTodos() {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM autor";
            PreparedStatement statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    public void excluir(Autor autor) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM autor WHERE id_autor = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, autor.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
            super.excluir(autor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
