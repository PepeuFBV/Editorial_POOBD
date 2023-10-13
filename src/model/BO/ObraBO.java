package model.BO;

import model.DAO.ObraDAO;
import model.VO.ObraVO;


public class ObraBO {

	static private ObraDAO obraDAO = new ObraDAO();

    public void adicionar(ObraVO obraVO) {
        obraDAO.inserir(obraVO);
    }

    public void atualizar(ObraVO obraVO) {
        obraDAO.atualizar(obraVO);
    }
    
    public void excluir(ObraVO obraVO) {
        obraDAO.excluir(obraVO);
    }
}
	        
