package controller;

import java.util.ArrayList;
import java.util.List;

import exceptions.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.BO.AvaliadorBO;
import model.BO.ObraBO;
import model.DAO.AvaliadorDAO;
import model.DAO.ObraDAO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;

public class DefinirAvaliadorController {

	@FXML
	private ChoiceBox<String> titulos;
	
	@FXML
	private ChoiceBox<String> avaliadorBox;
	
    public void initialize() {
        carregarObrasAvaliadorPendente();
        carregarEmailsAvaliadores();
    }
    
    private void carregarObrasAvaliadorPendente() {
        ObraBO obraBO = new ObraBO();
        ObraVO obraVO = new ObraVO();
        obraVO.setStatus("Avaliador Pendente");

        try {
            ArrayList<ObraVO> obras = obraBO.buscarPorStatus(obraVO);
            for (ObraVO obra : obras) {
            	titulos.getItems().add(obra.getTitulo());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void carregarEmailsAvaliadores() {
        AvaliadorBO avaliadorBO = new AvaliadorBO();

        try {
            List<AvaliadorVO> avaliadores = avaliadorBO.listar();
            for (AvaliadorVO avaliador : avaliadores) {
            	avaliadorBox.getItems().add(avaliador.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    @FXML
    public void concluir(ActionEvent event) throws NotFoundException {
        String emailSelecionado = avaliadorBox.getValue();
        String obraSelecionada = titulos.getValue();

        if (emailSelecionado != null && !emailSelecionado.isEmpty() || obraSelecionada != null && !obraSelecionada.isEmpty()) {
            AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
            AvaliadorVO avaliadorBuscado = new AvaliadorVO();
            avaliadorBuscado.setEmail(emailSelecionado);
            
            ObraDAO obraDAO = new ObraDAO();
            ObraVO obraBuscada = new ObraVO();
            obraBuscada.setTitulo(obraSelecionada);

            try {
                ArrayList<AvaliadorVO> avaliadoresEncontrados = avaliadorDAO.buscarPorEmail(avaliadorBuscado);
                ArrayList<ObraVO> obrasEncontradas = obraDAO.buscarPorTitulo(obraBuscada);

                if (!avaliadoresEncontrados.isEmpty() || !obrasEncontradas.isEmpty()) {

                    AvaliadorVO avaliadorSelecionado = avaliadoresEncontrados.get(0);
                    
                    AvaliadorVO avaliadorDefinido = new AvaliadorVO();
                    
                    avaliadorDefinido.setCpf(avaliadorSelecionado.getCpf());
                    avaliadorDefinido.setEmail(emailSelecionado);
                    avaliadorDefinido.setEndereco(avaliadorSelecionado.getEndereco());
                    avaliadorDefinido.setIDAvaliador(avaliadorSelecionado.getIDAvaliador());
                    avaliadorDefinido.setIDUsuario(avaliadorSelecionado.getIDUsuario());
                    avaliadorDefinido.setNome(avaliadorSelecionado.getNome());
                    avaliadorDefinido.setSenha(avaliadorSelecionado.getSenha());
                    avaliadorDefinido.setTipo(avaliadorSelecionado.getTipo());
                    
                    ObraVO obraEscolhida = obrasEncontradas.get(0);
                    
                    ObraVO obraDefinida = new ObraVO();
                    
                    obraDefinida.setAno(obraEscolhida.getAno());
                    obraDefinida.setAutor(obraEscolhida.getAutor());
                    obraDefinida.setAvaliador(obraEscolhida.getAvaliador());
                    obraDefinida.setDataAvaliacao(obraEscolhida.getDataAvaliacao());
                    obraDefinida.setGenero(obraEscolhida.getGenero());
                    obraDefinida.setIDObra(obraEscolhida.getIDObra());
                    obraDefinida.setPdfAvaliacao(obraEscolhida.getPdfAvaliacao());
                    obraDefinida.setPdfObra(obraEscolhida.getPdfObra());
                    obraDefinida.setStatus(obraEscolhida.getStatus());
                    obraDefinida.setTitulo(obraSelecionada);
                    
                } else {
                	throw new NotFoundException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Você precisa selecionar os items.");
        }
    }

	@FXML
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) avaliadorBox.getScene().getWindow();
	    stage.close();
	}
}
