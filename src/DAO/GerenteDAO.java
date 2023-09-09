package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entities.Gerente;
import model.entities.Usuario;

public class GerenteDAO extends UserDAO {

    @Override
    public void inserir(Usuario entity) {
        Connection con = getConnection();

        if (entity instanceof Gerente) {
            Gerente gerente = (Gerente) entity;

            String comando = "INSERT INTO sua_tabela (id, nome, login, senha) VALUES (?, ?, ?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(comando);
                ps.setString(1, gerente.getId());
                ps.setString(2, gerente.getNome());
                ps.setString(3, gerente.getLogin());
                ps.setString(4, gerente.getSenha());
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

        if (entity instanceof Gerente) {
            Gerente gerente = (Gerente) entity;

            String comando = "UPDATE sua_tabela SET nome = ?, login = ?, senha = ? WHERE id = ?";
            try {
                PreparedStatement ps = con.prepareStatement(comando);
                ps.setString(1, gerente.getNome());
                ps.setString(2, gerente.getLogin());
                ps.setString(3, gerente.getSenha());
                ps.setString(4, gerente.getId());
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
