package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entities.Gerente;

public class GerenteDAO extends UsersDAO {
    
    public void inserir(Gerente gerente) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO gerente (id_gerente, nome, login, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, gerente.getId());
            statement.setString(2, gerente.getNome());
            statement.setString(3, gerente.getLogin());
            statement.setString(4, gerente.getSenha());
            statement.executeUpdate();
            statement.close();
            super.inserir(gerente);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Gerente gerente) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE gerente SET nome = ?, login = ?, senha = ? WHERE id_gerente = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, gerente.getNome());
            statement.setString(2, gerente.getLogin());
            statement.setString(3, gerente.getSenha());
            statement.setInt(4, gerente.getId());
            statement.executeUpdate();
            statement.close();
            super.atualizar(gerente);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet buscarPorId(Gerente gerente) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM gerente WHERE id_gerente = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, gerente.getId());
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
            String sql = "SELECT * FROM gerente";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void excluir(Gerente gerente) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM gerente WHERE id_gerente = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, gerente.getId());
            statement.executeUpdate();
            statement.close();
            super.excluir(gerente);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
