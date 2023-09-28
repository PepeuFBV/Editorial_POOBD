package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.entities.Gerente;

public class GerenteDAO extends UsersDAO {
    
    public void inserir(Gerente gerente) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO gerente (nome, login, senha) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, gerente.getNome());
            statement.setString(2, gerente.getLogin());
            statement.setString(3, gerente.getSenha());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                gerente.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }
            statement.close();
            BaseDAOImpl.closeConnection();
            super.inserir(gerente);
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
            BaseDAOImpl.closeConnection();
            super.atualizar(gerente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet buscarPorLogin(Gerente gerente) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM gerente WHERE login = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, gerente.getLogin());
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet listar() {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM gerente";
            PreparedStatement statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void excluir(Gerente gerente) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM gerente WHERE id_gerente = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, gerente.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
            super.excluir(gerente);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
