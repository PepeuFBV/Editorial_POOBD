package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.DAO.AvaliadorDAO;
import model.DAO.ObraDAO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;
import exceptions.NotFoundException;

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
        ObraDAO obraDAO = new ObraDAO();
        ObraVO obraVO = new ObraVO();
        obraVO.setStatus("Avaliador Pendente");

        try {
            ArrayList<ObraVO> obras = obraDAO.buscarPorStatus(obraVO);
            for (ObraVO obra : obras) {
            	titulos.getItems().add(obra.getTitulo());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void carregarEmailsAvaliadores() {
        AvaliadorDAO avaliadorDAO = new AvaliadorDAO();

        try {
            ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.listar();
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
            } catch (SQLException e) {
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
