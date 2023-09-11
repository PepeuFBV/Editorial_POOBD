package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AvaliadorDAO extends UserDAO<AvaliadorVO> {
    
    @Override
    public void inserir(AvaliadorVO vo) {
        try {
            super.inserir(vo);
            String sql = "INSERT INTO avaliador (id_user, nome, login, senha, endereco, cpf) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getId());
            ps.setString(2, vo.getNome());
            ps.setString(3, vo.getLogin());
            ps.setString(4, vo.getSenha());
            ps.setString(5, vo.getEndereco());
            ps.setString(6, vo.getCpf());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na criação do avaliador. Nenhuma linha foi criada.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                vo.setId(rs.getLong("id"));
            } else {
                throw new SQLException("Falha na criação do avaliador. Nenhum ID foi retornado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void atualizar(AvaliadorVO vo) {
        try {
            super.atualizar(vo);
            String sql = "UPDATE avaliador SET nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id = ?";
            PreparedStatement ps;
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNome());
            ps.setString(2, vo.getLogin());
            ps.setString(3, vo.getSenha());
            ps.setString(4, vo.getEndereco());
            ps.setString(5, vo.getCpf());
            ps.setLong(6, vo.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na atualização do avaliador. Nenhuma linha foi atualizada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet buscarPorId(AvaliadorVO vo) {
        String sql = "SELECT * FROM avaliador WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());
            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca do avaliador. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet listarTodos(AvaliadorVO vo) {
        String sql = "SELECT * FROM avaliador";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca do avaliador. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(AvaliadorVO vo) {
        String sql = "DELETE FROM avaliador WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na exclusão do avaliador. Nenhuma linha foi excluída.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
