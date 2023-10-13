package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;

public class AvaliadorDAO extends UsersDAO<AvaliadorVO> {
    
    @Override
    public void inserir(AvaliadorVO avaliador) {
        Connection con = null;
        
        try {
            super.inserir(avaliador);
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
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

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(AvaliadorVO avaliador) {
        Connection con = null;
        
        try {
            super.atualizar(avaliador);
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "UPDATE avaliador SET nome = ?, email = ?, senha = ?, cpf = ? WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getNome());
            statement.setString(2, avaliador.getEmail());
            statement.setString(3, avaliador.getSenha());
            statement.setString(4, avaliador.getCpf());
            statement.setLong(5, avaliador.getIDAvaliador());
            statement.executeUpdate();

            statement.close();
        	BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<AvaliadorVO> buscarPorId(AvaliadorVO avaliador) {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, avaliador.getIDAvaliador());
            rs = statement.executeQuery();
            while (rs.next()) {
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setTipo(rs.getString("tipo"));
                avaliador.setNome(rs.getString("nome"));
                avaliador.setEndereco(rs.getString("endereco"));
                avaliador.setCpf(rs.getString("cpf"));
                avaliador.setEmail(rs.getString("email"));
                avaliador.setSenha(rs.getString("senha"));

                avaliadores.add(avaliador);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avaliadores;
    }

    public ArrayList<AvaliadorVO> buscarPorEmail(AvaliadorVO avaliador) {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getEmail());
            rs = statement.executeQuery();
            while (rs.next()) {
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setTipo(rs.getString("tipo"));
                avaliador.setNome(rs.getString("nome"));
                avaliador.setEndereco(rs.getString("endereco"));
                avaliador.setCpf(rs.getString("cpf"));
                avaliador.setEmail(rs.getString("email"));
                avaliador.setSenha(rs.getString("senha"));

                avaliadores.add(avaliador);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avaliadores;
    }
        
    public ArrayList<AvaliadorVO> buscarPorNome(AvaliadorVO avaliador) {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE nome LIKE ? AND tipo = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + avaliador.getNome() + "%");
            statement.setString(2, avaliador.getTipo());
            rs = statement.executeQuery();
            while (rs.next()) {
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setTipo(rs.getString("tipo"));
                avaliador.setNome(rs.getString("nome"));
                avaliador.setEndereco(rs.getString("endereco"));
                avaliador.setCpf(rs.getString("cpf"));
                avaliador.setEmail(rs.getString("email"));
                avaliador.setSenha(rs.getString("senha"));
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avaliadores;
    }

    public ArrayList<AvaliadorVO> buscarPorObras(ObraVO obra) {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT a.* FROM avaliador a JOIN obra o ON a.id_avaliador = o.id_avaliador WHERE o.id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            rs = statement.executeQuery();
            while (rs.next()) {
                AvaliadorVO avaliador = new AvaliadorVO(); //usado para salvar a cada linha do ResultSet
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setTipo(rs.getString("tipo"));
                avaliador.setNome(rs.getString("nome"));
                avaliador.setEndereco(rs.getString("endereco"));
                avaliador.setCpf(rs.getString("cpf"));
                avaliador.setEmail(rs.getString("email"));
                avaliador.setSenha(rs.getString("senha"));

                avaliadores.add(avaliador);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avaliadores;
    }

    @Override
    public ArrayList<AvaliadorVO> listar() {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM avaliador";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                AvaliadorVO avaliador = new AvaliadorVO(); //usado para salvar a cada linha do ResultSet
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setTipo(rs.getString("tipo"));
                avaliador.setNome(rs.getString("nome"));
                avaliador.setEndereco(rs.getString("endereco"));
                avaliador.setCpf(rs.getString("cpf"));
                avaliador.setEmail(rs.getString("email"));
                avaliador.setSenha(rs.getString("senha"));

                avaliadores.add(avaliador);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avaliadores;
    }

    @Override
    public void excluir(AvaliadorVO avaliador) {
        Connection con = null;
        
        try {
            super.excluir(avaliador);
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "DELETE FROM avaliador WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, avaliador.getIDAvaliador());
            statement.executeUpdate();

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
