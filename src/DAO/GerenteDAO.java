package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entities.Gerente;

public class GerenteDAO<E> extends UserDAO<E> {
    

    @Override
    public void inserir(E entity) {

        //chamar metodo de conexao aqui
        Connection con = ConnectionFactory.getConnection(); //remover essa linha

        //realizar downcasting, checar se está correto depois
        Gerente gerente = (Gerente) entity;

        String comando = "INSERT INTO TABLE _______ VALUES (?,?,?,?)"; //colocar comando SQL de inserção na tabela aqui
        try {
            PreparedStatement ps = con.prepareStatement(comando); //alterar o statement de acordo com a tabela no SQL
            ps.setString(1, gerente.getId());
            ps.setString(2, gerente.getNome());
            ps.setString(3, gerente.getLogin());
            ps.setString(4, gerente.getSenha());
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

        Gerente gerente = (Gerente) entity;

        String comando = "UPDATE TABLE _______ nome = ?, login = ?, senha = ? WHERE id = ?"; //colocar comando SQL de atualização na tabela aqui
    
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, gerente.getNome());
            ps.setString(2, gerente.getLogin());
            ps.setString(3, gerente.getSenha());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
