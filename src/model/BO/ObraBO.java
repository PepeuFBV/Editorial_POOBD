package model.BO;

import java.util.List;
import model.DAO.ObraDAO;
import model.VO.ObraVO;


public class ObraBO {

	private ObraDAO obraDAO = new ObraDAO();

    public void cadastrar(ObraVO obraVO) {
        obraDAO.inserir(obraVO);
    }

    public List<ObraVO> listar() {
        List<ObraVO> lista = obraDAO.listar();
        return lista;
    }

    public void atualizar(ObraVO obraVO) {
        obraDAO.atualizar(obraVO);
    }
    
    public void excluir(ObraVO obraVO) {
        obraDAO.excluir(obraVO);
    }
}
	        
