package controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.BO.AvaliadorBO;
import model.DAO.AvaliadorDAO;
import model.VO.AvaliadorVO;

public class DeletarAvaliadorController {
	
	@FXML
	private Label erroDeletarAvaliador;
	
	@FXML
	private ChoiceBox<String> email;
	
	@FXML
	private void initialize() {
	    carregarEmailsAvaliadores();
	}

	private void carregarEmailsAvaliadores() {
	    AvaliadorDAO avaliadorDAO = new AvaliadorDAO();
	    ArrayList<AvaliadorVO> avaliadores = avaliadorDAO.listar();

	    ObservableList<String> emailsList = FXCollections.observableArrayList();
	    for (AvaliadorVO avaliador : avaliadores) {
	        emailsList.add(avaliador.getEmail());
	    }

	    email.setItems(emailsList);
	}
	
	@FXML
	private void deletar(ActionEvent event) {
	    String emailSelecionado = email.getValue();

	    if (emailSelecionado != null && !emailSelecionado.isEmpty()) {
            AvaliadorBO avaliadorBO = new AvaliadorBO();
            AvaliadorVO avaliador = new AvaliadorVO();
            avaliador.setEmail(emailSelecionado);

            List<AvaliadorVO> avaliadores = avaliadorBO.buscarPorEmail(avaliador);

	        if (!avaliadores.isEmpty()) {
	            AvaliadorVO avaliadorParaExcluir = avaliadores.get(0);
	            
	            avaliadorBO.excluir(avaliadorParaExcluir);

	            erroDeletarAvaliador.setText("Avaliador excluído com sucesso.");
	            erroDeletarAvaliador.setVisible(true);
	        } else {
	            erroDeletarAvaliador.setText("Avaliador não encontrado.");
	            erroDeletarAvaliador.setVisible(true);
	        }
	    } else {
	        erroDeletarAvaliador.setText("Por favor, selecione um avaliador para excluir.");
	        erroDeletarAvaliador.setVisible(true);
	    }
	}


    
    @FXML
    private void voltar(ActionEvent event) {
	    Stage stage = (Stage) erroDeletarAvaliador.getScene().getWindow();
	    stage.close();
    }
}


