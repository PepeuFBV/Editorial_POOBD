package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO<VO extends UserVO> extends BaseDAOImpl<VO> {
    
    @Override
    public void inserir(VO vo) {
        String sql = "INSERT INTO usuario (nome, login, senha, endereco, cpf, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNome());
            ps.setString(2, vo.getLogin());
            ps.setString(3, vo.getSenha());
            if (vo.getEndereco() == null && vo.isInstanceof(GerenteVO)) {
                ps.setNull(4, java.sql.Types.VARCHAR);
            } else {
                ps.setString(4, vo.getEndereco());
            }
            if (vo.getCpf() == null && vo.isInstanceof(GerenteVO)) {
                ps.setNull(5, java.sql.Types.VARCHAR); //checar se isso funciona
            } else {
                ps.setString(5, vo.getCpf());
            }
            ps.setString(6, vo.getClass()); //alterar?

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na criação do usuário. Nenhuma linha foi criada.");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                vo.setId(rs.getLong("id"));
            } else {
                throw new SQLException("Falha na criação do usuário. Nenhum ID foi retornado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(Vo vo) {
        String sql = "UPDATE usuario SET nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, vo.getNome());
            ps.setString(2, vo.getLogin());
            ps.setString(3, vo.getSenha());
            ps.setString(4, vo.getEndereco());
            ps.setString(5, vo.getCpf());
            ps.setLong(6, vo.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na alteração do usuário. Nenhuma linha foi alterada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet buscarPorId(VO vo) {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());

            ResultSet rs = ps.executeQuery();
            if (rs != null) { //checar se está correto, mudar provavelmente
                return rs;
            } else {
                throw new SQLException("Falha na busca do usuário. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public ResultSet listarTodos(VO vo) { //deve se excluir os usuarios gerentes?
        String sql = "SELECT * FROM usuario";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                return rs;
            } else {
                throw new SQLException("Falha na listagem dos usuários. Nenhuma linha foi encontrada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(VO vo) {
        String sql = "DELETE FROM usuario WHERE id = ?";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setLong(1, vo.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Falha na exclusão do usuário. Nenhuma linha foi excluída.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
