package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;

public class AvaliadorDAO extends BaseDAOImpl<AvaliadorVO> {
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    public void inserir(AvaliadorVO avaliadorVO) {
        Connection con = null;
        usuarioDAO.inserir(avaliadorVO);
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "INSERT INTO avaliadores (nome, email, senha, cpf, endereco, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, avaliadorVO.getNome());
            statement.setString(2, avaliadorVO.getEmail());
            statement.setString(3, avaliadorVO.getSenha());
            statement.setString(4, avaliadorVO.getCpf());
            statement.setString(5, avaliadorVO.getEndereco());
            statement.setLong(6, avaliadorVO.getIDUsuario());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                avaliadorVO.setIDAvaliador(generatedKeys.getLong(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }

            statement.close();
            BaseDAOImpl.closeConnection();

            //para adicionar o ID_Usuario na tabela de avaliadores
            ArrayList<UsuarioVO> avaliador = usuarioDAO.buscarPorEmail(avaliadorVO);
            avaliadorVO.setIDUsuario(avaliador.get(0).getIDUsuario());

            AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
            avaliadorDAO.atualizar(avaliadorVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(AvaliadorVO avaliador) {
        Connection con = null;
        usuarioDAO.atualizar(avaliador);
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "UPDATE avaliadores SET nome = ?, email = ?, senha = ?, cpf = ?, endereco = ?, id_usuario = ? WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getNome());
            statement.setString(2, avaliador.getEmail());
            statement.setString(3, avaliador.getSenha());
            statement.setString(4, avaliador.getCpf());
            statement.setString(5, avaliador.getEndereco());
            statement.setLong(6, avaliador.getIDUsuario());
            statement.setLong(7, avaliador.getIDAvaliador());
            statement.executeUpdate();
            
            statement.close();
        	BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AvaliadorVO> buscarPorId(AvaliadorVO avaliador) {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM avaliadores WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, avaliador.getIDAvaliador());
            rs = statement.executeQuery();
            while (rs.next()) {
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setTipo("Avaliador");
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
            String sql = "SELECT * FROM avaliadores WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getEmail());
            rs = statement.executeQuery();
            while (rs.next()) { //deve retornar só 1
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setTipo("Avaliador");
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

    public ArrayList<AvaliadorVO> buscarPorEmailIncompleto(AvaliadorVO avaliador) {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM avaliadores WHERE email LIKE ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + avaliador.getEmail() + "%");
            rs = statement.executeQuery();
            while (rs.next()) { //deve retornar só 1
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setTipo("Avaliador");
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
            String sql = "SELECT * FROM avaliadores WHERE nome = ";
            statement = con.prepareStatement(sql);
            statement.setString(1, avaliador.getNome());
            rs = statement.executeQuery();
            while (rs.next()) {
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setTipo("Avaliador");
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

    public ArrayList<AvaliadorVO> buscarPorNomeIncompleto(AvaliadorVO avaliador) {
        Connection con = null;
        ArrayList<AvaliadorVO> avaliadores = new ArrayList<AvaliadorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM avaliadores WHERE nome LIKE ? ";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + avaliador.getNome() + "%");
            rs = statement.executeQuery();
            while (rs.next()) {
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setTipo("Avaliador");
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
            String sql = "SELECT a.* FROM avaliadores a JOIN obra o ON a.id_avaliador = o.id_avaliador WHERE o.id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            rs = statement.executeQuery();
            while (rs.next()) {
                AvaliadorVO avaliador = new AvaliadorVO(); //usado para salvar a cada linha do ResultSet
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setTipo("Avaliador");
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
            String sql = "SELECT * FROM avaliadores";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                AvaliadorVO avaliador = new AvaliadorVO(); //usado para salvar a cada linha do ResultSet
                avaliador.setIDAvaliador(rs.getLong("id_avaliador"));
                avaliador.setIDUsuario(rs.getLong("id_usuario"));
                avaliador.setTipo("Avaliador");
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
        usuarioDAO.excluir(avaliador);
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "DELETE FROM avaliadores WHERE id_avaliador = ?";
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
