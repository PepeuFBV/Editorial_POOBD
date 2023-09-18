package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import model.entities.Obra;

public class ObraDAO implements BaseDAO<Obra> {
    
    public void inserir(Obra obra) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO obra (id_obra, id_autor, titulo, genero, ano, status) VALUES (?, ?, ?, ?, ?, ?)";
            java.sql.PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getId());
            statement.setInt(2, obra.getAutor().getId());
            statement.setString(3, obra.getTitulo());
            statement.setString(4, obra.getGenero());
            statement.setDate(5, obra.getAno()); //consertar
            statement.setString(6, obra.getStatus());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void atualizar(Obra obra) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "UPDATE obra SET id_avaliador = ?, titulo = ?, genero = ?, ano = ?, status = ? WHERE id_obra = ?";
            java.sql.PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getAvaliador().getId());
            statement.setString(2, obra.getTitulo());
            statement.setString(3, obra.getGenero());
            statement.setDate(4, obra.getAno()); //consertar
            statement.setString(5, obra.getStatus());
            statement.setInt(6, obra.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet buscarPorId(Obra obra) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE id_obra = ?";
            java.sql.PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getId());
            ResultSet rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ResultSet listarTodos() {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra";
            java.sql.PreparedStatement statement = con.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            BaseDAOImpl.closeConnection();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void excluir(Obra obra) {
        try {
            Connection con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM obra WHERE id_obra = ?";
            java.sql.PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getId());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
