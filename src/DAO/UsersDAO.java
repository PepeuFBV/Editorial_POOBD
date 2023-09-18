package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.entities.User;

public class UsersDAO implements BaseDAO<User> {
    
    public void inserir(User user) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO users (id_usuario, tipo, login, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getClassName());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getSenha());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void atualizar(User user) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE users SET login = ?, senha = ? WHERE id_usuario = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getSenha());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet buscarPorId(User user) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE id_usuario = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, user.getId());
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
            String sql = "SELECT * FROM users";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void excluir(User user) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM users WHERE id_usuario = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
