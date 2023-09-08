package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.entities.Autor;

public class AutorDAO<E> extends UserDAO<E> {
    

    @Override
    public void inserir(E entity) {

        //chamar metodo de conexao aqui
        Connection con = ConnectionFactory.getConnection(); //remover essa linha
 
        Autor autor = (Autor) entity; //está correto?

        String comando = "INSERT INTO TABLE _______ VALUES (?,?,?,?,?,?)"; //colocar comando SQL de inserção na tabela aqui
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
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(E entity) {
        Connection con = ConnectionFactory.getConnection();

        Autor autor = (Autor) entity;

        String comando = "UPDATE TABLE _______ nome = ?, login = ?, senha = ?, endereco = ?, cpf = ? WHERE id = ?"; //colocar comando SQL de atualização na tabela aqui
    
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getLogin());
            ps.setString(3, autor.getSenha());
            ps.setString(4, autor.getEndereco());
            ps.setString(5, autor.getCpf());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
