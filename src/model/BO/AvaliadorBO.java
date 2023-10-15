package model.BO;

import java.util.ArrayList;
import java.util.List;

import model.DAO.AvaliadorDAO;
import model.DAO.ObraDAO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import model.VO.UsuarioVO;

public class AvaliadorBO {

    private AvaliadorDAO avaliadorDAO = new AvaliadorDAO();

    public void cadastrar(AvaliadorVO avaliadorVO) {
        avaliadorDAO.inserir(avaliadorVO);
    }

    public List<AvaliadorVO> listar() {
        List<AvaliadorVO> avaliadores = avaliadorDAO.listar();
        return avaliadores;
    }

    public List<ObraVO> listarObrasAvaliadas(AvaliadorVO autorVO) {
        ObraDAO obraDAO = new ObraDAO();
        List<ObraVO> obras = obraDAO.buscarPorAvaliador(autorVO);
        return obras;
    }

    public AvaliadorVO convertToAvaliadorVO(UsuarioVO usuarioVO) {
        AvaliadorVO avaliadorVO = new AvaliadorVO();
        avaliadorVO.setEmail(usuarioVO.getEmail());

        ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorEmail(avaliadorVO);
        for (AvaliadorVO avaliador : avaliadores) { // só retornará 1 avaliador
            avaliadorVO = avaliador;
        }
        
        return avaliadorVO;
    }

}
