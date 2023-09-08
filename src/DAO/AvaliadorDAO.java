package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entities.Avaliador;

public class AvaliadorDAO<E> extends UserDAO<E> {
    

    @Override
    public void inserir(E entity) {

        //chamar metodo de conexao aqui
        Connection con = ConnectionFactory.getConnection(); //remover essa linha
 
        Avaliador avaliador = (Avaliador) entity; //está correto?

        String comando = "INSERT INTO TABLE _______ VALUES (?,?,?,?,?,?)"; //colocar comando SQL de inserção na tabela aqui
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
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(E entity) {
        Connection con = ConnectionFactory.getConnection();

        Avaliador avaliador = (Avaliador) entity;

        String comando = "UPDATE TABLE _______ nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id = ?"; //colocar comando SQL de atualização na tabela aqui
    
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, avaliador.getNome());
            ps.setString(2, avaliador.getLogin());
            ps.setString(3, avaliador.getSenha());
            ps.setString(4, avaliador.getEndereco());
            ps.setString(5, avaliador.getCpf());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
