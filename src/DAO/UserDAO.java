package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entities.Usuario;

public abstract class UserDAO<E> extends BaseDAOImpl<E> { //corrigir a questão do E !!!!

    public Connection getConnection() {
        Connection con = ConnectionFactory.getConnection();
        return con;
    }

    @Override
    public abstract void inserir(E entity);

    @Override
    public abstract void atualizar(E entity);

    @Override
    public E buscar(E entity) { //haveram vários metódos buscar, um para cada tipo de busca, usando um listar diferente?
        Connection con = ConnectionFactory.getConnection();
        return null;
    }
    
    @Override
    public List<E> listar(E entity) {
        Connection con = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM _______"; //inserir nome da tabela de obras
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Obra> lista = new ArrayList<Obra>();
            while (rs.next()) {
                Obra obraAtual = new Obra();
                try {
                    obraAtual.setTitulo(rs.getString("titulo"));
                    //inserir outros dados a serem coletados na tabela sql 
                } catch (Exception e) {
                    e.printStackTrace();
                }
                obra.cadastrar(obraAtual); //adicionar em uma lista de obras
                lista.add(obraAtual);
            }
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        

        return null;
    }

    @Override
    public void excluir(E entity) {
        Connection con = ConnectionFactory.getConnection();

        Usuario usuario = (Usuario) entity;

        String comando = "DELETE FROM _______ WHERE id = ?"; //colocar comando SQL de exclusão na tabela aqui

        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, usuario.getId());
            ps.execute();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
