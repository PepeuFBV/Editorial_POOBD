package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.entities.Autor;

//TODO
//Adicionar na tabela escritaPor o id do autor

public class AutorDAO extends UsersDAO {
    
    public void inserir(Autor autor) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO autor (id_autor, nome, login, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, autor.getId());
            statement.setString(2, autor.getNome());
            statement.setString(3, autor.getLogin());
            statement.setString(4, autor.getSenha());
            statement.executeUpdate();
            statement.close();
            super.inserir(autor);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Autor autor) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE autor SET nome = ?, login = ?, senha = ? WHERE id_autor = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getLogin());
            statement.setString(3, autor.getSenha());
            statement.setInt(4, autor.getId());
            statement.executeUpdate();
            statement.close();
            super.atualizar(autor);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet buscarPorId(Autor autor) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM autor WHERE id_autor = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, autor.getId());
            ResultSet rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ResultSet listarTodos() {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM autor";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void excluir(Autor autor) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM autor WHERE id_autor = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, autor.getId());
            statement.executeUpdate();
            statement.close();
            super.excluir(autor);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
