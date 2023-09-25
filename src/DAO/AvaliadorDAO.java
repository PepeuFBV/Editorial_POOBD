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
            statement.setString(1, avaliador.getNome());
            statement.setString(2, avaliador.getLogin());
            statement.setString(3, avaliador.getSenha());
            statement.setString(4, avaliador.getEndereco());
            statement.setString(5, avaliador.getCpf());
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
            statement.setInt(6, avaliador.getId());
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

    public ResultSet buscarPorLogin(String login) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador WHERE login = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, login);
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
        
    public ResultSet buscarPorNome(Avaliador avaliador) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador WHERE nome LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + avaliador.getNome() + "%");
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet buscarPorEndereco(Avaliador avaliador) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador WHERE endereco LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + avaliador.getEndereco() + "%");
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    
    public ResultSet buscarPorCpf(Avaliador avaliador) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador WHERE cpf LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + avaliador.getCpf() + "%");
            rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet buscarPorObras(Avaliador avaliador) { //checar se está certo
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador WHERE id_avaliador IN (SELECT id_avaliador FROM obra_avaliador WHERE id_obra = ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, avaliador.getId());
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
