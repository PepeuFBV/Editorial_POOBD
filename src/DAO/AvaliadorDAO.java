package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.entities.Avaliador;

public class AvaliadorDAO extends UsersDAO {
    
    public void inserir(Avaliador avaliador) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO avaliador (nome, login, senha, endereco, cpf) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(2, avaliador.getNome());
            statement.setString(3, avaliador.getLogin());
            statement.setString(4, avaliador.getSenha());
            statement.setString(5, avaliador.getEndereco());
            statement.setString(6, avaliador.getCpf());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                avaliador.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }
            statement.executeUpdate();
            statement.close();  
            BaseDAOImpl.closeConnection();
            super.inserir(avaliador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Avaliador avaliador) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE avaliador SET nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id_avaliador = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getNome());
            statement.setString(2, avaliador.getLogin());
            statement.setString(3, avaliador.getSenha());
            statement.setString(4, avaliador.getEndereco());
            statement.setString(5, avaliador.getCpf());
            statement.setInt(4, avaliador.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
            super.atualizar(avaliador);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet buscarPorId(Avaliador avaliador) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador WHERE id_avaliador = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, avaliador.getId());
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
            String sql = "SELECT * FROM avaliador";
            PreparedStatement statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    public void excluir(Avaliador avaliador) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM avaliador WHERE id_avaliador = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, avaliador.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
            super.excluir(avaliador);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
