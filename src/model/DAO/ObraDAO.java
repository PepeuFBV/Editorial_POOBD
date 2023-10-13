package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import util.LerPDF;

public class ObraDAO {

    public void inserir(ObraVO obra) {
        Connection con = null;
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
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
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void atualizar(ObraVO obra) {
        Connection con = null;
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
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

            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ObraVO> buscarPorId(ObraVO obra) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra WHERE id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getIDObra());
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;
    }

    public ArrayList<ObraVO> buscarPorTitulo(ObraVO obra) throws SQLException {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra WHERE titulo LIKE ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + obra.getTitulo() + "%");
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;
    }

    public ArrayList<ObraVO> buscarPorAno(ObraVO obra) throws SQLException {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra WHERE ano = ?";
            statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(obra.getAno()));
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            statement.close();
            BaseDAOImpl.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;
    }

    public ArrayList<ObraVO> buscarPorAno(int anoInicio, int anoFinal) throws SQLException {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra WHERE ano BETWEEN ? AND ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, anoInicio);
            statement.setInt(2, anoFinal);
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            BaseDAOImpl.closeConnection();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;

    }

    public ArrayList<ObraVO> buscarPorStatus(ObraVO obra) throws SQLException {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra WHERE status LIKE ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, "%" + obra.getStatus() + "%");
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            BaseDAOImpl.closeConnection();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;
    }

    public ArrayList<ObraVO> buscarPorDataDeAvaliacao(ObraVO obra) throws SQLException {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra WHERE data_avaliacao = ?";
            statement = con.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(obra.getDataAvaliacao()));
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            BaseDAOImpl.closeConnection();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;
    }

    public ArrayList<ObraVO> buscarPorAutor(ObraVO obra) throws SQLException {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, obra.getAutor().getIDAutor());
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            BaseDAOImpl.closeConnection();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;
    }

    public ArrayList<ObraVO> listar() throws SQLException {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obra";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ResultSet autorRS = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (autorRS.next()) {
                    autorVO.setNome(autorRS.getString("nome"));
                    autorVO.setEndereco(autorRS.getString("endereco"));
                    autorVO.setCpf(autorRS.getString("cpf"));
                    autorVO.setEmail(autorRS.getString("email"));
                    autorVO.setSenha(autorRS.getString("senha"));
                    autorVO.setTipo(autorRS.getString("tipo"));
                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                ResultSet avaliadorRS = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar
                                                                               // no avaliadorVO
                if (avaliadorRS.next()) {
                    avaliadorVO.setNome(avaliadorRS.getString("nome"));
                    avaliadorVO.setEndereco(avaliadorRS.getString("endereco"));
                    avaliadorVO.setCpf(avaliadorRS.getString("cpf"));
                    avaliadorVO.setEmail(avaliadorRS.getString("email"));
                    avaliadorVO.setSenha(avaliadorRS.getString("senha"));
                    avaliadorVO.setTipo(avaliadorRS.getString("tipo"));
                    obraVO.setAvaliador(avaliadorVO);
                } else {
                    obraVO.setAvaliador(null);
                }
                arrayDeObras.add(obraVO);
            }
            BaseDAOImpl.closeConnection();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayDeObras;
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
        }
    }
}
