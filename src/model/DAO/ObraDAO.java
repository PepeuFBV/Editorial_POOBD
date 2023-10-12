package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.VO.ObraVO;
import util.LerPDF;

public class ObraDAO {
    
	public void inserir(ObraVO obra) {
	    Connection con = null;
	    PreparedStatement statement = null;
	    try {
	        con = BaseDAOImpl.getConnection();
	        String sql = "INSERT INTO obra (titulo, genero, ano, status, data_avaliacao, id_autor, id_avaliador, pdf_obra, pdf_avaliacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	        statement.setString(1, obra.getTitulo());
	        statement.setString(2, obra.getGenero());
	        statement.setDate(3, Date.valueOf(obra.getAno()));
	        statement.setString(4, obra.getStatus());
	        statement.setDate(5, Date.valueOf(obra.getDataAvaliacao()));
	        statement.setLong(6, obra.getAutor().getIDAutor());
	        statement.setLong(7, obra.getAvaliador().getIDAvaliador());
	        
	        byte[] pdfObraBytes = LerPDF.lerArquivoPDF(obra.getPdfObra());
	        statement.setBytes(8, pdfObraBytes);

	        byte[] pdfAvaliacaoBytes = LerPDF.lerArquivoPDF(obra.getPdfAvaliacao());
	        statement.setBytes(9, pdfAvaliacaoBytes);

	        int affectedRows = statement.executeUpdate();
	        if (affectedRows == 0) {
	            throw new Exception("A inserção falhou. Nenhuma linha foi alterada.");
	        }
	        ResultSet generatedKeys = statement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            obra.setIDObra(generatedKeys.getLong(1));
	        } else {
	            throw new Exception("A inserção falhou. Nenhum id foi retornado.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        BaseDAOImpl.closeConnection();
	    }
	}

	public void atualizar(ObraVO obra) {
	    Connection con = null;
	    PreparedStatement statement = null;
	    try {
	        con = BaseDAOImpl.getConnection();
	        String sql = "UPDATE obra SET titulo = ?, genero = ?, ano = ?, status = ?, data_avaliacao = ?, id_autor = ?, id_avaliador = ?, pdf_obra = ?, pdf_avaliacao = ? WHERE id_obra = ?";
	        statement = con.prepareStatement(sql);
	        statement.setString(1, obra.getTitulo());
	        statement.setString(2, obra.getGenero());
	        statement.setDate(3, Date.valueOf(obra.getAno()));
	        statement.setString(4, obra.getStatus());
	        statement.setDate(5, Date.valueOf(obra.getDataAvaliacao()));
	        statement.setLong(6, obra.getAutor().getIDAutor());
	        statement.setLong(7, obra.getAvaliador().getIDAvaliador());
	        
	        byte[] pdfObraBytes = LerPDF.lerArquivoPDF(obra.getPdfObra());
	        statement.setBytes(8, pdfObraBytes);

	        byte[] pdfAvaliacaoBytes = LerPDF.lerArquivoPDF(obra.getPdfAvaliacao());
	        statement.setBytes(9, pdfAvaliacaoBytes);

	        statement.setLong(10, obra.getIDObra());
	        statement.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        BaseDAOImpl.closeConnection();
	    }
	}

    public ResultSet buscarPorId(ObraVO obra) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;

    }
    
    public ResultSet buscarPorTitulo(ObraVO obra) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE titulo LIKE ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + obra.getTitulo() + "%");
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    public ResultSet buscarPorAno(ObraVO obra) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE ano = ?";
            statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(obra.getAno()));
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }
    
    public ResultSet buscarPorAno(int anoInicio, int anoFinal) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
        	con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE ano BETWEEN ? AND ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, anoInicio);
            statement.setInt(2, anoFinal);
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	BaseDAOImpl.closeConnection();
        }
        return rs;
    }


    public ResultSet buscarPorStatus(ObraVO obra) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE status LIKE ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + obra.getStatus() + "%");
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }
    
    public ResultSet buscarPorDataDeAvaliacao(ObraVO obra) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE data_avaliacao = ?";
            statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(obra.getDataAvaliacao()));
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    public ResultSet buscarPorAutor(ObraVO obra) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getAutor().getIDAutor());
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
    }

    public ResultSet listar() {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "SELECT * FROM obra";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
        return rs;
        
    }

    public void excluir(ObraVO obra) {
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = BaseDAOImpl.getConnection();
            String sql = "DELETE FROM obra WHERE id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            statement.executeUpdate();
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAOImpl.closeConnection();
        }
    }
}
