package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.VO.UsuarioVO;

public class UsersDAO<User extends UsuarioVO> extends BaseDAOImpl<User> implements BaseDAO<User> {
    
	@Override
    public void inserir(UsuarioVO user) {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = BaseDAOImpl.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
    public void atualizar(UsuarioVO user) {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = BaseDAOImpl.getConnection();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
    public ResultSet buscarPorId(UsuarioVO user) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE id_usuario = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, user.getIDUsuario());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return rs;
    }

    public ResultSet buscarPorNome(UsuarioVO user) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE nome = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, user.getNome());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return rs;
    }

    public ResultSet buscarPorEmail(UsuarioVO user) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return rs;
    }

	@Override
    public ResultSet listar() {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return rs;
    }

	@Override
    public void excluir(UsuarioVO user) {
        Connection con = null;
        PreparedStatement statement = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM users WHERE id_usuario = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, user.getIDUsuario());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

}
