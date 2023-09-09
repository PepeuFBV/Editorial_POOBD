package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entities.Usuario;

public abstract class UserDAO extends BaseDAOImpl<Usuario> {

    @Override
    public abstract void inserir(Usuario entity); 

    @Override
    public abstract void atualizar(Usuario entity);

    @Override
    public Usuario buscar(Usuario entity) {
        if (entity instanceof Usuario) {
            Usuario usuario = (Usuario) entity;
            try (Connection con = getConnection()) {
                String sql = "SELECT * FROM usuario WHERE id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, usuario.getId());
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEndereco(rs.getString("endereco"));
                    usuario.setCpf(rs.getString("cpf"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Tipo de entidade não suportado");
        }
        return entity;
    }

    @Override
    public List<Usuario> listar(Usuario entity) {
        if (entity instanceof Usuario) {
            List<Usuario> usuarios = new ArrayList<>();
            try (Connection con = getConnection()) {
                String sql = "SELECT * FROM usuario";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getString("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setLogin(rs.getString("login"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEndereco(rs.getString("endereco"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuarios.add(usuario);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return usuarios;
        } else {
            throw new IllegalArgumentException("Tipo de entidade não suportado");
        }
    }

    @Override
    public void excluir(Usuario entity) {
        if (entity instanceof Usuario) {
            Usuario usuario = (Usuario) entity;
            try (Connection con = getConnection()) {
                String sql = "DELETE FROM usuario WHERE id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, usuario.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Tipo de entidade não suportado");
        }
    }
}
