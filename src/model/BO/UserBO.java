package model.BO;

import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ErroLoginException;
import exceptions.InsertException;
import model.DAO.AutorDAO;
import model.DAO.AvaliadorDAO;
import model.DAO.BaseDAO;
import model.DAO.GerenteDAO;
import model.DAO.UsersDAO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.GerenteVO;
import model.VO.UsuarioVO;

public class UserBO<VO extends UsuarioVO> {

	static private BaseDAO<UsuarioVO> usuDAO = new UsersDAO<UsuarioVO>();
	static private BaseDAO<GerenteVO> gerDAO = new GerenteDAO();
	static private BaseDAO<AutorVO> autDAO = new AutorDAO();
	static private BaseDAO<AvaliadorVO> avaDAO = new AvaliadorDAO();
	
	public UsuarioVO autenticar(UsuarioVO vo) throws ErroLoginException {
	    ResultSet usuRS = usuDAO.buscarPorEmail(vo); 
	    try {
	        if (usuRS.next()) {
	            if (usuRS.getString("senha").equals(vo.getSenha())) {
	                GerenteVO ger = new GerenteVO();
	                ger.setIDUsuario(usuRS.getLong("id_usuario"));
	                
	                ResultSet gerRS = gerDAO.buscarPorId(ger);
	                if (gerRS.next()) {
	                    ger.setEmail(vo.getEmail());
	                    ger.setCpf(usuRS.getString("cpf"));
	                    ger.setNome(usuRS.getString("nome"));
	                    ger.setEndereco(usuRS.getString("endereco"));
	                    ger.setIDUsuario(usuRS.getLong("id_usuario"));
	                    ger.setTipo(usuRS.getString("tipo"));
	                    return ger;
	                } else {
	                    AutorVO aut = new AutorVO();
	                    aut.setIDUsuario(usuRS.getLong("id_usuario"));
	                    
	                    ResultSet autRS = autDAO.buscarPorId(aut);
	                    if (autRS.next()) {
	                        aut.setEmail(vo.getEmail());
	                        aut.setCpf(usuRS.getString("cpf"));
	                        aut.setNome(usuRS.getString("nome"));
	                        aut.setEndereco(usuRS.getString("endereco"));
	                        aut.setIDUsuario(usuRS.getLong("id_usuario"));
	                        aut.setTipo(usuRS.getString("tipo"));
	                        return aut;
	                    } else {
	                        AvaliadorVO ava = new AvaliadorVO();
	                        ava.setIDUsuario(usuRS.getLong("id_usuario"));
	                        
	                        ResultSet avaRS = avaDAO.buscarPorId(ava);
	                        if (avaRS.next()) {
	                            ava.setEmail(vo.getEmail());
	                            ava.setCpf(usuRS.getString("cpf"));
	                            ava.setNome(usuRS.getString("nome"));
	                            ava.setEndereco(usuRS.getString("endereco"));
	                            ava.setIDUsuario(usuRS.getLong("id_usuario"));
	                            ava.setTipo(usuRS.getString("tipo"));
	                            return ava;
	                        }
	        	            else throw new ErroLoginException();
	                    }
	                }
	            }
	            else throw new ErroLoginException();

	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
    	throw new ErroLoginException();
	}
	
	public void cadastrar(UsuarioVO vo) throws InsertException{
		try {
			ResultSet rs = usuDAO.buscarPorEmail(vo);
			if(rs.next()) {
				throw new InsertException("O email que você inseriu já existe");
			} else {
				usuDAO.inserir(vo);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}