package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entities.Avaliador;
import model.entities.Usuario;

public class AvaliadorDAO extends UserDAO {

    @Override
    public void inserir(Usuario entity) {
        Connection con = getConnection();

        if (entity instanceof Avaliador) {
            Avaliador avaliador = (Avaliador) entity;

            String comando = "INSERT INTO sua_tabela (id, nome, login, senha, endereco, cpf) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = con.prepareStatement(comando);
                ps.setString(1, avaliador.getId());
                ps.setString(2, avaliador.getNome());
                ps.setString(3, avaliador.getLogin());
                ps.setString(4, avaliador.getSenha());
                ps.setString(5, avaliador.getEndereco());
                ps.setString(6, avaliador.getCpf());
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

        if (entity instanceof Avaliador) {
            Avaliador avaliador = (Avaliador) entity;

            String comando = "UPDATE sua_tabela SET nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id = ?";
            try {
                PreparedStatement ps = con.prepareStatement(comando);
                ps.setString(1, avaliador.getNome());
                ps.setString(2, avaliador.getLogin());
                ps.setString(3, avaliador.getSenha());
                ps.setString(4, avaliador.getEndereco());
                ps.setString(5, avaliador.getCpf());
                ps.setString(6, avaliador.getId());
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
