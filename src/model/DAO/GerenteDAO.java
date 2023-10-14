package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.VO.GerenteVO;
import model.VO.UsuarioVO;

public class GerenteDAO extends BaseDAOImpl<GerenteVO> {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
    public void inserir(GerenteVO gerenteVO) {
        usuarioDAO.inserir(gerenteVO);
		Connection con = null;
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "INSERT INTO gerentes (nome, email, senha, id_usuario) VALUES (?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, gerenteVO.getNome());
            statement.setString(2, gerenteVO.getEmail());
            statement.setString(3, gerenteVO.getSenha());
            statement.setLong(4, gerenteVO.getIDUsuario());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                gerenteVO.setIDGerente(generatedKeys.getLong(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }

            statement.close();
            BaseDAOImpl.closeConnection();

            //para adicionar o ID_Usuario na tabela de gerentes
            ArrayList<UsuarioVO> gerente = usuarioDAO.buscarPorEmail(gerenteVO);
            gerenteVO.setIDUsuario(gerente.get(0).getIDUsuario());

            GerenteDAO gerenteDAO = new GerenteDAO();
            gerenteDAO.atualizar(gerenteVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    
    @Override
    public void atualizar(GerenteVO gerenteVO) {
        Connection con = null;
        usuarioDAO.atualizar(gerenteVO);
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "UPDATE gerentes SET nome = ?, email = ?, senha = ?, id_usuario = ? WHERE id_gerente = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, gerenteVO.getNome());
            statement.setString(2, gerenteVO.getEmail());
            statement.setString(3, gerenteVO.getSenha());
            statement.setLong(4, gerenteVO.getIDUsuario());
            statement.setLong(5, gerenteVO.getIDGerente());
            statement.executeUpdate();

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                gerenteVO.setIDUsuario(rs.getLong("id_usuario"));
                gerenteVO.setTipo("Gerente");
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
                gerenteVO.setIDUsuario(rs.getLong("id_usuario"));
                gerenteVO.setTipo("Gerente");
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
    public void excluir(GerenteVO gerenteVO) {
        Connection con = null;
        usuarioDAO.excluir(gerenteVO);

        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "DELETE FROM gerentes WHERE id_gerente = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, gerenteVO.getIDGerente());
            statement.executeUpdate();

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
