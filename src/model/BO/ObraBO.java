package model.BO;

import model.DAO.ObraDAO;
import model.VO.ObraVO;


public class ObraBO {

	static private ObraDAO obraDAO = new ObraDAO();

	public void adicionarObra(ObraVO vo) {
	    obraDAO.inserir(vo);
	}
}
	        
