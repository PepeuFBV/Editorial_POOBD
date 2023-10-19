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

    public List<AvaliadorVO> listarAvaliadoresPelaBusca(String busca) {
        
        AvaliadorVO avaliadorVO= new AvaliadorVO();
        List<AvaliadorVO> list = new ArrayList<>();

        avaliadorVO.setNome(busca);
        List<AvaliadorVO> listBuscaPorNome = avaliadorDAO.buscarPorNomeIncompleto(avaliadorVO);
        for (int i = 0; i < listBuscaPorNome.size(); i++) { //retorna avaliadores com esse nome no sistema
            list.add(listBuscaPorNome.get(i));
        }

        avaliadorVO.setEmail(busca);
        List<AvaliadorVO> listBuscaPorEmail = avaliadorDAO.buscarPorEmailIncompleto(avaliadorVO);
        for (int i = 0; i < listBuscaPorEmail.size(); i++) { //retorna avaliadores com esse email no sistema
            list.add(listBuscaPorEmail.get(i));
        }

        //checa na própria lista e retira itens com id repetido
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) { //compara com os próximos
                if (list.get(i).getIDAvaliador() == list.get(j).getIDAvaliador()) {
                    list.remove(j);
                    j--;
                }
            }
        }

        return list;
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
    
    public void excluir(AvaliadorVO avaliadorVO) {
    	avaliadorDAO.excluir(avaliadorVO);
    }
    
    public void atualizar(AvaliadorVO avaliadorVO) {
    	avaliadorDAO.atualizar(avaliadorVO);
    }
    
    public List<AvaliadorVO> buscarPorEmail (AvaliadorVO avaliadorVO) {
    	ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorEmail(avaliadorVO);
        return avaliadores;
    }   
    
    public List<AvaliadorVO> buscarPorId (AvaliadorVO avaliadorVO) {
    	ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.buscarPorId(avaliadorVO);
        return avaliadores;
    }
}
