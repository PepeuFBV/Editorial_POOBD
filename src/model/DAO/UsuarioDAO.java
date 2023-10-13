package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.VO.UsuarioVO;

public class UsuarioDAO extends BaseDAOImpl<UsuarioVO> {
    
	@Override
    public void inserir(UsuarioVO user) {
        Connection con = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "INSERT INTO users (tipo, nome, endereco, cpf, email, senha) VALUES (?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getTipo());
            statement.setString(2, user.getNome());
            statement.setString(3, user.getEndereco());
            statement.setString(4, user.getCpf());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getSenha());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setIDUsuario(generatedKeys.getLong(1));
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
    public void atualizar(UsuarioVO user) {
        Connection con = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "UPDATE users SET nome = ?, endereco = ?, cpf = ?, email = ?, senha = ?, tipo = ? WHERE id_usuario = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, user.getNome());
            statement.setString(2, user.getEndereco());
            statement.setString(3, user.getCpf());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getSenha());
            statement.setString(6, user.getTipo());
            statement.setLong(7, user.getIDUsuario());
            statement.executeUpdate();

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UsuarioVO> buscarPorId(UsuarioVO usuarioVO) { //pegar o tipo de users
        Connection con = null;
        ArrayList<UsuarioVO> users = new ArrayList<UsuarioVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE id_usuario = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, usuarioVO.getIDUsuario()); //o uso do método de UserVO não é permitido?
            rs = statement.executeQuery();
            while (rs.next()) {
                UsuarioVO userVO = new UsuarioVO(); //usado para salvar a cada linha do ResultSet
                userVO.setIDUsuario(rs.getLong("id_usuario"));
                userVO.setTipo(rs.getString("tipo"));
                userVO.setNome(rs.getString("nome"));
                userVO.setEndereco(rs.getString("endereco"));
                userVO.setCpf(rs.getString("cpf"));
                userVO.setEmail(rs.getString("email"));
                userVO.setSenha(rs.getString("senha"));

                users.add(userVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<UsuarioVO> buscarPorNome(UsuarioVO user) {
        Connection con = null;
        ArrayList<UsuarioVO> users = new ArrayList<UsuarioVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE nome = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, user.getNome());
            rs = statement.executeQuery();
            while (rs.next()) {
                UsuarioVO userVO = new UsuarioVO(); //usado para salvar a cada linha do ResultSet
                userVO.setIDUsuario(rs.getLong("id_usuario"));
                userVO.setTipo(rs.getString("tipo"));
                userVO.setNome(rs.getString("nome"));
                userVO.setEndereco(rs.getString("endereco"));
                userVO.setCpf(rs.getString("cpf"));
                userVO.setEmail(rs.getString("email"));
                userVO.setSenha(rs.getString("senha"));

                users.add(userVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public ArrayList<UsuarioVO> buscarPorEmail(UsuarioVO user) {
        Connection con = null;
        ArrayList<UsuarioVO> users = new ArrayList<UsuarioVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            rs = statement.executeQuery();
            while (rs.next()) {
                UsuarioVO userVO = new UsuarioVO(); //usado para salvar a cada linha do ResultSet
                userVO.setIDUsuario(rs.getLong("id_usuario"));
                userVO.setTipo(rs.getString("tipo"));
                userVO.setNome(rs.getString("nome"));
                userVO.setEndereco(rs.getString("endereco"));
                userVO.setCpf(rs.getString("cpf"));
                userVO.setEmail(rs.getString("email"));
                userVO.setSenha(rs.getString("senha"));

                users.add(userVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

	@Override
    public ArrayList<UsuarioVO> listar() {
        Connection con = null;
        ArrayList<UsuarioVO> users = new ArrayList<UsuarioVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                UsuarioVO userVO = new UsuarioVO(); //usado para salvar a cada linha do ResultSet
                userVO.setIDUsuario(rs.getLong("id_usuario"));
                userVO.setTipo(rs.getString("tipo"));
                userVO.setNome(rs.getString("nome"));
                userVO.setEndereco(rs.getString("endereco"));
                userVO.setCpf(rs.getString("cpf"));
                userVO.setEmail(rs.getString("email"));
                userVO.setSenha(rs.getString("senha"));

                users.add(userVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

	@Override
    public void excluir(UsuarioVO user) {
        Connection con = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "DELETE FROM users WHERE id_usuario = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, user.getIDUsuario());
            statement.executeUpdate();

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

}
