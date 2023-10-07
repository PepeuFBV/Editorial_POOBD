package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.VO.AvaliadorVO;
import model.VO.ObraVO;

public class AvaliadorDAO extends UsersDAO<AvaliadorVO> {
    
    @Override
    public void inserir(AvaliadorVO avaliador) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.inserir(avaliador);
            con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO avaliador (nome, email, senha, cpf, id_usuario) VALUES (?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, avaliador.getNome());
            statement.setString(2, avaliador.getEmail());
            statement.setString(3, avaliador.getSenha());
            statement.setString(4, avaliador.getCpf());
            statement.setLong(5, avaliador.getIDUsuario());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                avaliador.setIDAvaliador(generatedKeys.getLong(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	BaseDAOImpl.closeConnection();
        }
    }

    @Override
    public void atualizar(AvaliadorVO avaliador) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.atualizar(avaliador);
            con = BaseDAOImpl.getConnection();
            String sql = "UPDATE avaliador SET nome = ?, email = ?, senha = ?, cpf = ? WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getNome());
            statement.setString(2, avaliador.getEmail());
            statement.setString(3, avaliador.getSenha());
            statement.setString(4, avaliador.getCpf());
            statement.setLong(5, avaliador.getIDAvaliador());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	BaseDAOImpl.closeConnection();
        }
    }

    @Override
    public ResultSet buscarPorId(AvaliadorVO avaliador) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, avaliador.getIDAvaliador());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    public ResultSet buscarPorEmail(AvaliadorVO avaliador) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getEmail());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }
        
    public ResultSet buscarPorNome(AvaliadorVO avaliador) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE nome LIKE ? AND tipo = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + avaliador.getNome() + "%");
            statement.setString(2, avaliador.getTipo());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    public ResultSet buscarPorObras(ObraVO obra) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT a.* FROM avaliador a JOIN obra o ON a.id_avaliador = o.id_avaliador WHERE o.id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    @Override
    public ResultSet listar() {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM avaliador";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    @Override
    public void excluir(AvaliadorVO avaliador) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.excluir(avaliador);
            con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM avaliador WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, avaliador.getIDAvaliador());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
    }
}
