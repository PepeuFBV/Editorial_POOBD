package model.BO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.DAO.AutorDAO;
import model.DAO.AvaliadorDAO;
import model.DAO.ObraDAO;
import model.VO.AutorVO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import view.Telas;


public class ObraBO {

	private ObraDAO obraDAO = new ObraDAO();

    public void cadastrar(ObraVO obraVO) {
        obraDAO.inserir(obraVO);
    }

    public List<ObraVO> listar() {
        List<ObraVO> lista = obraDAO.listar();
        return lista;
    }

    public List<ObraVO> listarObrasPelaBusca(String busca) { //buscando por título, autor, avaliador ou ano 

        ObraVO obraVO = new ObraVO();
        List<ObraVO> list = new ArrayList<>();

        obraVO.setTitulo(busca);
        List<ObraVO> listBuscaPorTitulo = obraDAO.buscarPorTituloIncompleto(obraVO);
        for (int i = 0; i < listBuscaPorTitulo.size(); i++) { //retorna obras com esse título no sistema
            list.add(listBuscaPorTitulo.get(i));
        }

        AutorVO autorVO = new AutorVO();
        autorVO.setNome(busca);
        AutorDAO autorDAO = new AutorDAO();
        List<AutorVO> autoresPossiveis = autorDAO.buscarPorNomeIncompleto(autorVO); //retorna autores com esse nome incompleto no sistema
        for (int i = 0; i < autoresPossiveis.size(); i++) {
            List<ObraVO> listBuscaPorAutor = obraDAO.buscarPorAutor(autoresPossiveis.get(i));
            for (int j = 0; j < listBuscaPorAutor.size(); j++) {
                list.add(listBuscaPorAutor.get(j));
            }
        }

        AvaliadorVO avaliadorVO = new AvaliadorVO();
        avaliadorVO.setNome(busca);
        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
        List<AvaliadorVO> avaliadoresPossiveis = avaliadorDAO.buscarPorNomeIncompleto(avaliadorVO); //retorna avaliadores com esse nome incompleto no sistema
        for (int i = 0; i < avaliadoresPossiveis.size(); i++) {
            List<ObraVO> listBuscaPorAvaliador = obraDAO.buscarPorAvaliador(avaliadoresPossiveis.get(i));
            for (int j = 0; j < listBuscaPorAvaliador.size(); j++) {
                list.add(listBuscaPorAvaliador.get(j));
            }
        }

        if (busca.matches("[0-9]+")) { //se busca for apenas número inteiro
            while (busca.length() < 4) { //se busca for apenas ano, completa com zeros à esquerda até ter 4 dígitos
                busca += "0";
            }
            busca = busca + "-01-01"; //transforma o ano em "ano-01-01" para ser comparado com o ano da obra
            LocalDate ano = LocalDate.parse(busca);
            obraVO.setAno(ano);
            List<ObraVO> listBuscaPorAno = obraDAO.buscarPorAnoIncompleto(obraVO);
            for (int i = 0; i < listBuscaPorAno.size(); i++) {
                list.add(listBuscaPorAno.get(i));
            }
        }

        //checa na própria lista e retira itens com id repetido
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) { //compara com os próximos
                if (list.get(i).getIDObra() == list.get(j).getIDObra()) {
                    list.remove(j);
                    j--;
                }
            }
        }

        return list;
    }
    
    public List<ObraVO> filtrarParaAutorAtual(List<ObraVO> obras) {
        List<ObraVO> list = new ArrayList<>();
        for (int i = 0; i < obras.size(); i++) {
            if (obras.get(i).getAutor().getEmail().equals(Telas.getUsuarioVOAtual().getEmail())) {
                list.add(obras.get(i));
            }
        }
        return list;
    }

    public List<ObraVO> filtrarParaAvaliadorAtual(List<ObraVO> obras) {
        List<ObraVO> list = new ArrayList<>();
        for (int i = 0; i < obras.size(); i++) {
            if (obras.get(i).getAvaliador().getEmail().equals(Telas.getUsuarioVOAtual().getEmail())) {
                list.add(obras.get(i));
            }
        }
        return list;
    }

    public void atualizar(ObraVO obraVO) {
        obraDAO.atualizar(obraVO);
    }
    
    public void excluir(ObraVO obraVO) {
        obraDAO.excluir(obraVO);
    }
}
	        
