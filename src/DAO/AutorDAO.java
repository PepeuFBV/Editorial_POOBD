package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutorDAO extends UserDAO<AutorVO> {
    
    @Override
    public void inserir(AutorVO vo) {
        try {
            super.inserir(vo);
            String sql = "INSERT INTO autor (id_user, nome, login, senha, endereco, cpf) VALUES (?, ?, ?, ?, ?, ?)";
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
                throw new SQLException("Falha na criação do autor. Nenhuma linha foi criada.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                vo.setId(rs.getLong("id"));
            } else {
                throw new SQLException("Falha na criação do autor. Nenhum ID foi retornado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void atualizar(AutorVO vo) {
        try {
            super.atualizar(vo);
            String sql = "UPDATE autor SET nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id = ?";
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
                throw new SQLException("Falha na atualização do autor. Nenhuma linha foi atualizada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet buscarPorId(AutorVO vo) {
        String sql = "SELECT * FROM autor WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());
            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca do autor. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet listarTodos(AutorVO vo) {
        String sql = "SELECT * FROM autor";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca do autor. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void excluir(AutorVO vo) {
        String sql = "DELETE FROM autor WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na exclusão do autor. Nenhuma linha foi excluída.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
