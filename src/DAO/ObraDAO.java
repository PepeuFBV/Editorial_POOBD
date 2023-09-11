package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObraDAO<VO> extends BaseDAOImpl<VO> {

    @Override
    public void inserir(ObraVO vo) { //alterar para obter o id do autor (pelo nome) durante a inserção
        try {
            String sql = "INSERT INTO obra (titulo, genero, ano, status, id_autor, id_avaliador) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getTitulo());
            ps.setString(2, vo.getGenero());
            ps.setInt(3, vo.getAno());
            ps.setString(4, vo.getStatus());
            ps.setLong(5, vo.getIdAutor());
            ps.setNull(4, java.sql.Types.VARCHAR);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void alterar(ObraVO vo) {
        try {
            String sql = "UPDATE obra SET titulo = ?, genero = ?, ano = ?, status = ?, id_autor = ?, id_avaliador = ? WHERE id = ?";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getTitulo());
            ps.setString(2, vo.getGenero());
            ps.setInt(3, vo.getAno());
            ps.setString(4, vo.getStatus());
            ps.setLong(5, vo.getIdAutor()); //deve se alterar no objeto vo antes
            ps.setLong(6, vo.getIdAvaliador());
            ps.setLong(7, vo.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ResultSet buscarPorId(ObraVO vo) {
        try {
            String sql = "SELECT * FROM obra WHERE id = ?";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());
            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca da obra. Nenhuma linha foi encontrada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet listarTodos(ObraVO vo) {
        try {
            String sql = "SELECT * FROM obra";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca da obra. Nenhuma linha foi encontrada.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(ObraVO vo) {
        try {
            String sql = "DELETE FROM obra WHERE id = ?";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
