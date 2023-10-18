package controller;

import java.util.ArrayList;
import java.util.List;

import exceptions.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.BO.AvaliadorBO;
import model.BO.ObraBO;
import model.VO.AvaliadorVO;
import model.VO.ObraVO;

public class DefinirAvaliadorController {
	
	@FXML
	private Button botaoDefinirAvaliador;
	
	@FXML
	private Label labelDefinirAvaliador;

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
            AvaliadorBO avaliadorBO = new AvaliadorBO();
            AvaliadorVO avaliadorBuscado = new AvaliadorVO();
            avaliadorBuscado.setEmail(emailSelecionado);
            
            ObraBO obraBO = new ObraBO();
            ObraVO obraBuscada = new ObraVO();
            obraBuscada.setTitulo(obraSelecionada);

            try {
                List<AvaliadorVO> avaliadoresEncontrados = avaliadorBO.buscarPorEmail(avaliadorBuscado);
                ArrayList<ObraVO> obrasEncontradas = obraBO.buscarPorTitulo(obraBuscada);

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
                    obraDefinida.setAvaliador(avaliadorDefinido);
                    obraDefinida.setDataAvaliacao(obraEscolhida.getDataAvaliacao());
                    obraDefinida.setGenero(obraEscolhida.getGenero());
                    obraDefinida.setIDObra(obraEscolhida.getIDObra());
                    obraDefinida.setPdfAvaliacao(obraEscolhida.getPdfAvaliacao());
                    obraDefinida.setPdfObra(obraEscolhida.getPdfObra());
                    obraDefinida.setStatus("Em avaliação");
                    obraDefinida.setTitulo(obraSelecionada);
                    
                    obraBO.atualizar(obraDefinida);
                    labelDefinirAvaliador.setVisible(true);
                    labelDefinirAvaliador.setText("Avaliador definido com sucesso.");
                    botaoDefinirAvaliador.setText("Fechar");
                    
                    
                } else {
                	throw new NotFoundException();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Você precisa selecionar os items.");
            labelDefinirAvaliador.setVisible(true);
            labelDefinirAvaliador.setText("Você precisa selecionar os items.");
        }
    }

	@FXML
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) avaliadorBox.getScene().getWindow();
	    stage.close();
	}
}
