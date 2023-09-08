package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entities.Obra;

public class ObraDAO<E> extends BaseDAOImpl<E> { //corrigir a questão do E !!!! e a questão do connection factory

    public Connection getConnection() {
        Connection con = ConnectionFactory.getConnection();
        return con;
    }

    @Override
    public void inserir(E entity) { //deve se receber E ou Obra?

        //chamar metodo de conexao aqui
        Connection con = ConnectionFactory.getConnection(); //remover essa linha
        
        //realizar downcasting, checar se está correto depois
        Obra obra = (Obra) entity;

        String comando = ""; //colocar comando SQL de inserção na tabela aqui
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, obra.getTitulo());
            //inserir outros dados a serem postos na tabela sql, todas as informações devem ser
            //inseridas por aqui
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
    }

    @Override
    public E buscar(E entity) {
        Connection con = ConnectionFactory.getConnection();
        return null;
    }
    
    @Override
    public List<E> listar(E entity) {
        
        //chamar metodo de conexao aqui
        Connection con = ConnectionFactory.getConnection(); //remover essa linha
        
        //realizar downcasting, checar se está correto depois
        Obra obra = (Obra) entity;


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
    } 

}
