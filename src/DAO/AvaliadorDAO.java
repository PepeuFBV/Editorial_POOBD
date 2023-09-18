package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.entities.Avaliador;

//TODO
//Adicionar na tabela avaliadaPor o id do avaliador

public class AvaliadorDAO extends UsersDAO {
    
    public void inserir(Avaliador avaliador) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO avaliador (id_avaliador, nome, login, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, avaliador.getId());
            statement.setString(2, avaliador.getNome());
            statement.setString(3, avaliador.getLogin());
            statement.setString(4, avaliador.getSenha());
            statement.executeUpdate();
            statement.close();
            super.inserir(avaliador);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Avaliador avaliador) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE avaliador SET nome = ?, login = ?, senha = ? WHERE id_avaliador = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getNome());
            statement.setString(2, avaliador.getLogin());
            statement.setString(3, avaliador.getSenha());
            statement.setInt(4, avaliador.getId());
            statement.executeUpdate();
            statement.close();
            super.atualizar(avaliador);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet buscarPorId(Avaliador avaliador) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador WHERE id_avaliador = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, avaliador.getId());
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
            String sql = "SELECT * FROM avaliador";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void excluir(Avaliador avaliador) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM avaliador WHERE id_avaliador = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, avaliador.getId());
            statement.executeUpdate();
            statement.close();
            super.excluir(avaliador);
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
