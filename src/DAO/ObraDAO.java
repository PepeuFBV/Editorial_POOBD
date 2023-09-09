package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import model.entities.Obra;

public class ObraDAO extends BaseDAOImpl<Obra> {

    @Override
    public void inserir(Obra entity) {
        Connection con = getConnection();
        String comando = "INSERT INTO tabela_obras (titulo, genero, ano, autor, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, entity.getTitulo());
            ps.setString(2, entity.getGenero());
            ps.setDate(3, java.sql.Date.valueOf(entity.getAno()));
            ps.setString(4, entity.getAutor().getId()); 
            ps.setString(5, entity.getStatus().name()); 
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void atualizar(Obra entity) {
        Connection con = getConnection();
        String comando = "UPDATE tabela_obras SET titulo = ?, genero = ?, ano = ?, autor = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, entity.getTitulo());
            ps.setString(2, entity.getGenero());
            ps.setDate(3, java.sql.Date.valueOf(entity.getAno()));
            ps.setString(4, entity.getAutor().getId());
            ps.setString(5, entity.getStatus().name());
            ps.setString(6, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public Obra buscar(Obra entity) {
        Connection con = getConnection();
        String sql = "SELECT * FROM tabela_obras WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, entity.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Obra obra = new Obra();
                obra.setId(rs.getString("id"));
                obra.setTitulo(rs.getString("titulo"));
                obra.setGenero(rs.getString("genero"));
                obra.setAno(rs.getDate("ano").toLocalDate());
                return obra;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public List<Obra> listar(Obra entity) {
        Connection con = getConnection();
        String sql = "SELECT * FROM tabela_obras";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Obra> obras = new ArrayList<>();
            while (rs.next()) {
                Obra obra = new Obra();
                obra.setId(rs.getString("id"));
                obra.setTitulo(rs.getString("titulo"));
                obra.setGenero(rs.getString("genero"));
                obra.setAno(rs.getDate("ano").toLocalDate());
                obras.add(obra);
            }
            return obras;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return null;
    }

    @Override
    public void excluir(Obra entity) {
        Connection con = getConnection();
        String comando = "DELETE FROM tabela_obras WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, entity.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
