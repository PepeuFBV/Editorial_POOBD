package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.VO.GerenteVO;

public class GerenteDAO extends UsersDAO<GerenteVO> {
    
	@Override
    public void inserir(GerenteVO gerente) {
		Connection con = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "INSERT INTO gerentes (nome, email, senha) VALUES (?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, gerente.getNome());
            statement.setString(2, gerente.getEmail());
            statement.setString(3, gerente.getSenha());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                gerente.setIDGerente(generatedKeys.getLong(1));
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
    public void atualizar(GerenteVO gerente) {
        Connection con = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "UPDATE gerentes SET nome = ?, email = ?, senha = ? WHERE id_gerente = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, gerente.getNome());
            statement.setString(2, gerente.getEmail());
            statement.setString(3, gerente.getSenha());
            statement.setLong(4, gerente.getIDGerente());
            statement.executeUpdate();

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<GerenteVO> buscarPorId(GerenteVO gerente) {
        Connection con = null;
        ArrayList<GerenteVO> gerentes = new ArrayList<GerenteVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM gerentes WHERE id_gerente = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, gerente.getIDGerente());
            rs = statement.executeQuery();
            while (rs.next()) {
                GerenteVO gerenteVO = new GerenteVO(); //usado para salvar a cada linha do ResultSet
                gerenteVO.setIDGerente(rs.getLong("id_gerente"));
                gerenteVO.setNome(rs.getString("nome"));
                gerenteVO.setEmail(rs.getString("email"));
                gerenteVO.setSenha(rs.getString("senha"));

                gerentes.add(gerenteVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gerentes;
    }

    @Override
    public ArrayList<GerenteVO> listar() {
        Connection con = null;
        ArrayList<GerenteVO> gerentes = new ArrayList<GerenteVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM gerentes";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                GerenteVO gerenteVO = new GerenteVO(); //usado para salvar a cada linha do ResultSet
                gerenteVO.setIDGerente(rs.getLong("id_gerente"));
                gerenteVO.setNome(rs.getString("nome"));
                gerenteVO.setEmail(rs.getString("email"));
                gerenteVO.setSenha(rs.getString("senha"));

                gerentes.add(gerenteVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gerentes;
    }

    @Override
    public void excluir(GerenteVO gerente) {
        Connection con = null;
        
        try {
            super.excluir(gerente);
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "DELETE FROM gerentes WHERE id_gerente = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, gerente.getIDGerente());
            statement.executeUpdate();

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}