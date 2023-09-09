package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entities.Autor;
import model.entities.Usuario;

public class AutorDAO extends UserDAO {

    @Override
    public void inserir(Usuario entity) {
        Connection con = getConnection();

        if (entity instanceof Autor) {
            Autor autor = (Autor) entity;

            String comando = "INSERT INTO sua_tabela (id, nome, login, senha, endereco, cpf) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(comando);
                ps.setString(1, autor.getId());
                ps.setString(2, autor.getNome());
                ps.setString(3, autor.getLogin());
                ps.setString(4, autor.getSenha());
                ps.setString(5, autor.getEndereco());
                ps.setString(6, autor.getCpf());
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        } else {
            throw new IllegalArgumentException("Tipo de entidade não suportado");
        }
    }

    @Override
    public void atualizar(Usuario entity) {
        Connection con = getConnection();

        if (entity instanceof Autor) {
            Autor autor = (Autor) entity;

            String comando = "UPDATE sua_tabela SET nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id = ?";
            try {
                PreparedStatement ps = con.prepareStatement(comando);
                ps.setString(1, autor.getNome());
                ps.setString(2, autor.getLogin());
                ps.setString(3, autor.getSenha());
                ps.setString(4, autor.getEndereco());
                ps.setString(5, autor.getCpf());
                ps.setString(6, autor.getId());
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeConnection();
            }
        } else {
            throw new IllegalArgumentException("Tipo de entidade não suportado");
        }
    }
}
