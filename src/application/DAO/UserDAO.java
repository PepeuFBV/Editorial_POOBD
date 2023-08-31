package application.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.entities.Obra;

public class UserDAO implements UserInterface {

    @Override
    public void inserir(Obra obra) {
        Connection con = ConnectionFactory.getConnection();
        String comando = ""; //colocar comando SQL aqui
        try {
            PreparedStatement ps = con.prepareStatement(comando);
            ps.setString(1, obra.getTitulo());
            //inserir outros dados a serem postos na tabela sql
            ps.execute();

            ps.close();
            con.close();

        } catch (SQLException e) {   
            e.printStackTrace();
        }
    }

    @Override
    public void deletar(Obra obra) {
        Connection con = ConnectionFactory.getConnection();
    }

    @Override
    public void atualizar(Obra obra) {
        Connection con = ConnectionFactory.getConnection();
    }

    @Override
    public Obra buscar(Obra obra) {
        Connection con = ConnectionFactory.getConnection();
        return null;
    }
    
    @Override
    public List listar(Obra obra) {
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

}
