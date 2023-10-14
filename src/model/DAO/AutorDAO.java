package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.VO.AutorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;

public class AutorDAO extends BaseDAOImpl<AutorVO> {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    @Override
    public void inserir(AutorVO autorVO) {
        Connection con = null;
        usuarioDAO.inserir(autorVO);
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "INSERT INTO autores (nome, email, senha, cpf, endereco, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, autorVO.getNome());
            statement.setString(2, autorVO.getEmail());
            statement.setString(3, autorVO.getSenha());
            statement.setString(4, autorVO.getCpf());
            statement.setString(5, autorVO.getEndereco());
            statement.setLong(6, autorVO.getIDUsuario());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                autorVO.setIDAutor(generatedKeys.getLong(1));
            } else {
                throw new Exception("A inserção falhou. Nenhum id foi retornado.");
            }
            
            statement.close();
            BaseDAOImpl.closeConnection();

            //para adicionar o ID_Usuario na tabela de autores
            ArrayList<UsuarioVO> autor = usuarioDAO.buscarPorEmail(autorVO);
            autorVO.setIDUsuario(autor.get(0).getIDUsuario());

            AutorDAO autorDAO = new AutorDAO();
            autorDAO.atualizar(autorVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(AutorVO autor) {
        Connection con = null;
        usuarioDAO.atualizar(autor);
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "UPDATE autores SET nome = ?, email = ?, senha = ?, cpf = ?, endereco = ?, id_usuario = ? WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.setString(2, autor.getEmail());
            statement.setString(3, autor.getSenha());
            statement.setString(4, autor.getCpf());
            statement.setString(5, autor.getEndereco());
            statement.setLong(6, autor.getIDUsuario());
            statement.setLong(7, autor.getIDAutor());
            statement.executeUpdate();
            
            statement.close();
        	BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<AutorVO> buscarPorId(AutorVO autor) {
        Connection con = null;
        ArrayList<AutorVO> autores = new ArrayList<AutorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM autores WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, autor.getIDAutor());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setIDAutor(rs.getLong("id_autor"));
                autorVO.setTipo("Autor");
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

    public ArrayList<AutorVO> buscarPorEmail(AutorVO autor) {
        Connection con = null;
        PreparedStatement statement = null;
        ArrayList<AutorVO> autores = new ArrayList<AutorVO>();
        try {
            con = BaseDAOImpl.getConnection();
            ResultSet rs = null;
            String sql = "SELECT * FROM autores WHERE email = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, autor.getEmail());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setIDAutor(rs.getLong("id_autor"));
                autorVO.setTipo("Autor");
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
            String sql = "SELECT * FROM autores WHERE nome LIKE ? AND tipo = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + autor.getNome() + "%");
            statement.setString(2, autor.getTipo());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setIDAutor(rs.getLong("id_autor"));
                autorVO.setTipo("Autor");
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
            String sql = "SELECT a.* FROM autores a JOIN obra o ON a.id_autor = o.id_autor WHERE o.id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setIDAutor(rs.getLong("id_autor"));
                autorVO.setTipo(rs.getString("Autor"));
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
            String sql = "SELECT * FROM autores";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                AutorVO autorVO = new AutorVO(); //usado para salvar a cada linha do ResultSet
                autorVO.setIDUsuario(rs.getLong("id_usuario"));
                autorVO.setIDAutor(rs.getLong("id_autor"));
                autorVO.setTipo("Autor");
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
        usuarioDAO.excluir(autor);
        
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "DELETE FROM autores WHERE id_autor = ?";
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
