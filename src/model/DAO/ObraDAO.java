package model.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;

//TODO
//parar a inserção de obras sem autor

public class ObraDAO extends BaseDAOImpl<ObraVO> {

    public void inserir(ObraVO obra) { // apenas para a primeira inserção
        Connection con = null;
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            String sql = "INSERT INTO obras (titulo, genero, ano, status, id_autor, pdf_obra, data_avaliacao, id_avaliador, pdf_avaliacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, obra.getTitulo());
            statement.setString(2, obra.getGenero());
            statement.setDate(3, Date.valueOf(obra.getAno()));
            statement.setString(4, obra.getStatus());
            statement.setLong(5, obra.getAutor().getIDAutor());
            statement.setBytes(6, obra.getPdfObra());
            if (obra.getDataAvaliacao() == null) {
                statement.setDate(7, null);
            } else {
                statement.setDate(7, Date.valueOf(obra.getDataAvaliacao()));
            }
            if (obra.getAvaliador() == null) {
                statement.setNull(8, Types.BIGINT);
            } else {
                statement.setLong(8, obra.getAvaliador().getIDAvaliador());
            }
            if (obra.getPdfAvaliacao() == null) {
                statement.setBytes(9, null);
            } else {
                statement.setBytes(9, obra.getPdfAvaliacao());
            }

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
            String sql = "UPDATE obras SET titulo = ?, genero = ?, ano = ?, status = ?, data_avaliacao = ?, id_autor = ?, id_avaliador = ?, pdf_obra = ?, pdf_avaliacao = ? WHERE id_obra = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, obra.getTitulo());
            statement.setString(2, obra.getGenero());
            statement.setDate(3, Date.valueOf(obra.getAno()));
            statement.setString(4, obra.getStatus());
            if (obra.getDataAvaliacao() == null) {
                statement.setDate(5, null);
            } else { // se a data de avaliação não for nula (se a obra foi avaliada)
                statement.setDate(5, Date.valueOf(obra.getDataAvaliacao()));
            }
            statement.setLong(6, obra.getAutor().getIDAutor());
            if (obra.getAvaliador() == null) {
                statement.setNull(7, Types.BIGINT);
            } else {
                statement.setLong(7, obra.getAvaliador().getIDAvaliador());
            }
            statement.setBytes(8, obra.getPdfObra());
            if (obra.getPdfAvaliacao() == null) {
                statement.setBytes(9, null);
            } else {
                statement.setBytes(9, obra.getPdfAvaliacao());
            }
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
            String sql = "SELECT * FROM obras WHERE id_obra = ?";
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
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no
                                                                            // autorVO, esse array só terá um valor
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else { // exception
                    throw new Exception("Autor não encontrado");
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o
                                                                 // avaliador
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador
                                                                                                // pelo id e colocar no
                                                                                                // avaliadorVO
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(autores.get(0).getNome());
                        avaliadorVO.setEndereco(autores.get(0).getEndereco());
                        avaliadorVO.setCpf(autores.get(0).getCpf());
                        avaliadorVO.setEmail(autores.get(0).getEmail());
                        avaliadorVO.setSenha(autores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    } else {
                        obraVO.setAvaliador(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorTitulo(ObraVO obra) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE titulo = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, obra.getTitulo());
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no
                                                                            // autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());
                    }
                    obraVO.setAvaliador(avaliadorVO);
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorTituloIncompleto(ObraVO obra) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE titulo ILIKE ?";
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
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no
                                                                            // autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorAno(ObraVO obra) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE ano = ?";
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
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar no avaliadorVO
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    } else {
                        obraVO.setAvaliador(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorAnoIncompleto(ObraVO obra) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE EXTRACT(YEAR FROM ano) >= ?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, obra.getAno().getYear());
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }
                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar no avaliadorVO
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    } else {
                        obraVO.setAvaliador(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorAno(Date anoInicio, Date anoFinal) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE data_avaliacao BETWEEN ? AND ?";
            statement = con.prepareStatement(sql);
            statement.setDate(1, anoInicio);
            statement.setDate(2, anoFinal);
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                } else {
                    obraVO.setDataAvaliacao(null);
                }

                if (rs.getString("status") != null) {
                    obraVO.setStatus(rs.getString("status"));
                } else {
                    obraVO.setStatus(null);
                }

                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                }

                AutorVO autorVO = new AutorVO();
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO);
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null); // p ara acusar erro
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorStatus(ObraVO obra) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE status LIKE ?";
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
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no  autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
            String sql = "SELECT * FROM obras WHERE data_avaliacao = ?";
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
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no
                                                                            // autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    } else {
                        throw new Exception("Avaliador não encontrado");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorAutor(AutorVO autor) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE id_autor = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, autor.getIDAutor());
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no
                                                                            // autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> buscarPorAvaliador(AvaliadorVO avaliador) {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras WHERE id_avaliador = ?";
            statement = con.prepareStatement(sql);
            statement.setLong(1, avaliador.getIDAvaliador());
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO(); // cria AvaliadorVO para atribuir o Id e achar o avaliador
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO); // buscar o avaliador pelo id e colocar no avaliadorVO
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    } else {
                        obraVO.setAvaliador(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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

    public ArrayList<ObraVO> listar() {
        Connection con = null;
        ArrayList<ObraVO> arrayDeObras = new ArrayList<ObraVO>();
        try {
            con = BaseDAOImpl.getConnection();
            PreparedStatement statement = null;
            ResultSet rs = null;
            String sql = "SELECT * FROM obras";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                ObraVO obraVO = new ObraVO();
                obraVO.setIDObra(rs.getLong("id_obra"));
                obraVO.setTitulo(rs.getString("titulo"));
                obraVO.setGenero(rs.getString("genero"));
                obraVO.setAno(rs.getDate("ano").toLocalDate());
                obraVO.setStatus(rs.getString("status"));
                if (rs.getDate("data_avaliacao") != null) {
                    obraVO.setDataAvaliacao(rs.getDate("data_avaliacao").toLocalDate());
                }
                if (rs.getBytes("pdf_obra") != null) {
                    obraVO.setPdfObra(rs.getBytes("pdf_obra"));
                } else {
                    obraVO.setPdfObra(null); // para chamar exception
                }
                if (rs.getBytes("pdf_avaliacao") != null) {
                    obraVO.setPdfAvaliacao(rs.getBytes("pdf_avaliacao"));
                }

                AutorVO autorVO = new AutorVO(); // cria AutorVO para atribuir o Id e achar o autor
                autorVO.setIDAutor(rs.getLong("id_autor"));
                AutorDAO autorDAO = new AutorDAO();
                ArrayList<AutorVO> autores = autorDAO.buscarPorId(autorVO); // buscar o autor pelo id e colocar no
                                                                            // autorVO
                if (!autores.isEmpty()) {
                    autorVO.setIDAutor(autores.get(0).getIDAutor());
                    autorVO.setIDUsuario(autores.get(0).getIDUsuario());
                    autorVO.setTipo("Autor");
                    autorVO.setNome(autores.get(0).getNome());
                    autorVO.setEndereco(autores.get(0).getEndereco());
                    autorVO.setCpf(autores.get(0).getCpf());
                    autorVO.setEmail(autores.get(0).getEmail());
                    autorVO.setSenha(autores.get(0).getSenha());

                    obraVO.setAutor(autorVO);
                } else {
                    obraVO.setAutor(null);
                }

                try {
                    AvaliadorVO avaliadorVO = new AvaliadorVO();
                    avaliadorVO.setIDAvaliador(rs.getLong("id_avaliador"));
                    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
                    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
                    if (!avaliadores.isEmpty()) {
                        avaliadorVO.setIDAvaliador(avaliadores.get(0).getIDAvaliador());
                        avaliadorVO.setIDUsuario(avaliadores.get(0).getIDUsuario());
                        avaliadorVO.setTipo("Avaliador");
                        avaliadorVO.setNome(avaliadores.get(0).getNome());
                        avaliadorVO.setEndereco(avaliadores.get(0).getEndereco());
                        avaliadorVO.setCpf(avaliadores.get(0).getCpf());
                        avaliadorVO.setEmail(avaliadores.get(0).getEmail());
                        avaliadorVO.setSenha(avaliadores.get(0).getSenha());

                        obraVO.setAvaliador(avaliadorVO);
                    } else {
                        throw new Exception("Avaliador não encontrado");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
            String sql = "DELETE FROM obras WHERE id_obra = ?";
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
