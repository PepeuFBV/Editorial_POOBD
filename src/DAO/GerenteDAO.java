package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GerenteDAO extends UserDAO<GerenteVO> {
    
    @Override
    public void inserir(GerenteVO vo) {
        try {
            super.inserir(vo);
            String sql = "INSERT INTO gerente (id_user, nome, login, senha) VALUES (?, ?, ?, ?)";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getId());
            ps.setString(2, vo.getNome());
            ps.setString(3, vo.getLogin());
            ps.setString(4, vo.getSenha());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na criação do gerente. Nenhuma linha foi criada.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                vo.setId(rs.getLong("id"));
            } else {
                throw new SQLException("Falha na criação do gerente. Nenhum ID foi retornado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void atualizar(GerenteVO vo) {
        try {
            super.atualizar(vo);
            String sql = "UPDATE gerente SET nome = ?, login = ?, senha = ? WHERE id = ?";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNome());
            ps.setString(2, vo.getLogin());
            ps.setString(3, vo.getSenha());
            ps.setLong(4, vo.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na atualização do gerente. Nenhuma linha foi atualizada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet buscarPorId(GerenteVO vo) {
        String sql = "SELECT * FROM gerente WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());

            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca do gerente. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet listarTodos(GerenteVO vo) {
        String sql = "SELECT * FROM gerente";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca do gerente. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(GerenteVO vo) {
        String sql = "DELETE FROM gerente WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na exclusão do gerente. Nenhuma linha foi excluída.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
