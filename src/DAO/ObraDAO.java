package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import model.entities.Obra;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ObraDAO implements BaseDAO<Obra> {
    
    public void inserir(Obra obra) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO obra (titulo, genero, ano, status, id_autor) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, obra.getTitulo());
            statement.setString(2, obra.getGenero());
            statement.setDate(3, Date.valueOf(obra.getAno()));
            statement.setString(4, obra.getStatus());
            statement.setInt(5, obra.getAutor().getId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                obra.setId(generatedKeys.getInt(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizar(Obra obra) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE obra SET titulo = ?, genero = ?, ano = ?, status = ?, id_autor = ?, id_avaliador = ? WHERE id_obra = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, obra.getTitulo());
            statement.setString(2, obra.getGenero());
            statement.setDate(3, Date.valueOf(obra.getAno()));
            statement.setString(4, obra.getStatus());
            statement.setInt(5, obra.getAutor().getId());
            statement.setInt(6, obra.getAvaliador().getId());
            statement.setInt(5, obra.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet buscarPorId(Obra obra) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE id_obra = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getId());
            rs = statement.executeQuery();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }
    
    public ResultSet buscarPorTitulo(Obra obra) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE titulo LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + obra.getTitulo() + "%");
            rs = statement.executeQuery();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet buscarPorGenero(Obra obra) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE genero LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + obra.getGenero() + "%");
            rs = statement.executeQuery();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet buscarPorAno(Obra obra) { //checar se funciona
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE ano = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(obra.getAno()));
            rs = statement.executeQuery();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet buscarPorStatus(Obra obra) {
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE status LIKE ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, "%" + obra.getStatus() + "%");
            rs = statement.executeQuery();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet buscarPorAutor(Obra obra) { //consertar para se buscar pelo nome
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE id_autor = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getAutor().getId());
            rs = statement.executeQuery();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ResultSet buscarPorAvaliador(Obra obra) { //consertar para se buscar pelo nome
        ResultSet rs = null;
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE id_avaliador = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getAvaliador().getId());
            rs = statement.executeQuery();
            statement.close();
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
            String sql = "SELECT * FROM obra";
            PreparedStatement statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
        
    }

    public void excluir(Obra obra) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM obra WHERE id_obra = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
