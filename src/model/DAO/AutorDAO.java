package model.DAO;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.VO.AutorVO;
import model.VO.ObraVO;
import model.entities.Autor;

public class AutorDAO extends UsersDAO<AutorVO> {
    
    @Override
    public void inserir(AutorVO autor) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.inserir(autor);
            con = BaseDAOImpl.getConnection();
            String sql = "INSERT INTO autor (nome, email, senha, cpf, id_usuario) VALUES (?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getEmail());
            statement.setString(3, autor.getSenha());
            statement.setString(4, autor.getCpf());
            statement.setLong(5, autor.getIDUsuario());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                autor.setIDAutor(generatedKeys.getLong(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(AutorVO autor) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            super.atualizar(autor);
            con = BaseDAOImpl.getConnection();
            String sql = "UPDATE autor SET nome = ?, email = ?, senha = ?, cpf = ? WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getEmail());
            statement.setString(3, autor.getSenha());
            statement.setString(4, autor.getCpf());
            statement.setLong(5, autor.getIDAutor());
            statement.executeUpdate();

            statement.close();
        	BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<AutorVO> buscarPorId(AutorVO autor) {
        Connection con = null;
        ArrayList<AutorVO> autores = new ArrayList<AutorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, autor.getIDAutor());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setTipo(rs.getString("tipo"));
                autorVO.setNome(rs.getString("nome"));
                autorVO.setEndereco(rs.getString("endereco"));
                autorVO.setCpf(rs.getString("cpf"));
                autorVO.setEmail(rs.getString("email"));
                autorVO.setSenha(rs.getString("senha"));

                autores.add(autorVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public ArrayList<AutorVO> buscarPorEmail(AutorVO autor) {
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<AutorVO> autores = new ArrayList<AutorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, autor.getEmail());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setTipo(rs.getString("tipo"));
                autorVO.setNome(rs.getString("nome"));
                autorVO.setEndereco(rs.getString("endereco"));
                autorVO.setCpf(rs.getString("cpf"));
                autorVO.setEmail(rs.getString("email"));
                autorVO.setSenha(rs.getString("senha"));

                autores.add(autorVO);
            }
            
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    public ArrayList<AutorVO> buscarPorNome(AutorVO autor) {
        Connection con = null;
        ArrayList<AutorVO> autores = new ArrayList<AutorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM users WHERE nome LIKE ? AND tipo = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + autor.getNome() + "%");
            statement.setString(2, autor.getTipo());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setTipo(rs.getString("tipo"));
                autorVO.setNome(rs.getString("nome"));
                autorVO.setEndereco(rs.getString("endereco"));
                autorVO.setCpf(rs.getString("cpf"));
                autorVO.setEmail(rs.getString("email"));
                autorVO.setSenha(rs.getString("senha"));

                autores.add(autorVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    public ArrayList<AutorVO> buscarPorObras(ObraVO obra) {
        Connection con = null;
        ArrayList<AutorVO> autores = new ArrayList<AutorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT a.* FROM autor a JOIN obra o ON a.id_autor = o.id_autor WHERE o.id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setTipo(rs.getString("tipo"));
                autorVO.setNome(rs.getString("nome"));
                autorVO.setEndereco(rs.getString("endereco"));
                autorVO.setCpf(rs.getString("cpf"));
                autorVO.setEmail(rs.getString("email"));
                autorVO.setSenha(rs.getString("senha"));

                autores.add(autorVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    @Override
    public ArrayList<AutorVO> listar() {
        Connection con = null;
        ArrayList<AutorVO> autores = new ArrayList<AutorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM autor";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setTipo(rs.getString("tipo"));
                autorVO.setNome(rs.getString("nome"));
                autorVO.setEndereco(rs.getString("endereco"));
                autorVO.setCpf(rs.getString("cpf"));
                autorVO.setEmail(rs.getString("email"));
                autorVO.setSenha(rs.getString("senha"));

                autores.add(autorVO);
            }

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return autores;
    }

    @Override
    public void excluir(AutorVO autor) {
        Connection con = null;
        
        try {
            super.excluir(autor);
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "DELETE FROM autor WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, autor.getIDAutor());
            statement.executeUpdate();

            statement.close();
        	BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
