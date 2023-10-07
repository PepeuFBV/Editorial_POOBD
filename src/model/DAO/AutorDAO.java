package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.VO.AutorVO;
import model.VO.ObraVO;

public class AutorDAO extends UsersDAO<AutorVO> {
    
    @Override
    public void inserir(AutorVO autor) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.inserir(autor);
            con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO autor (nome, email, senha, cpf, id_usuario) VALUES (?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getEmail());
            statement.setString(3, autor.getSenha());
            statement.setString(4, autor.getCpf());
            statement.setLong(5, autor.getIDUsuario());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                autor.setIDAutor(generatedKeys.getLong(1));
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
    public void atualizar(AutorVO autor) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.atualizar(autor);
            con = BaseDAOImpl.getConnection();
            String sql = "UPDATE autor SET nome = ?, email = ?, senha = ?, cpf = ? WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getEmail());
            statement.setString(3, autor.getSenha());
            statement.setString(4, autor.getCpf());
            statement.setLong(5, autor.getIDAutor());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	BaseDAOImpl.closeConnection();
        }
    }

    @Override
    public ResultSet buscarPorId(AutorVO autor) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, autor.getIDAutor());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    public ResultSet buscarPorEmail(AutorVO autor) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, autor.getEmail());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    public ResultSet buscarPorNome(AutorVO autor) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM users WHERE nome LIKE ? AND tipo = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + autor.getNome() + "%");
            statement.setString(2, autor.getTipo());
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
            String sql = "SELECT a.* FROM autor a JOIN obra o ON a.id_autor = o.id_autor WHERE o.id_obra = ?";
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
            String sql = "SELECT * FROM autor";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDAOImpl.closeConnection();
        } 
        return rs;
    }

    @Override
    public void excluir(AutorVO autor) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.excluir(autor);
            con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM autor WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, autor.getIDAutor());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(statement, con);
        }
    }
}
