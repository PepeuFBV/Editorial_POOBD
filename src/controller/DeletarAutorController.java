package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DAO.AutorDAO;
import view.Telas;

public class DeletarAutorController {
	
	@FXML 
	private Label label1;
	
	@FXML 
	private Label label2;
	
	@FXML
	private Label erroDeletarAutor;
	
    @FXML
    private Button buttonSim;

    @FXML
    private Button buttonNao;
    
    @FXML
    private Button botao;

    @FXML
    private void handleSimButtonAction(ActionEvent event) {
    	AutorDAO autorDAO = new AutorDAO();
    	autorDAO.excluir(autorVO);
    	System.out.println("Autor excluído com sucesso.");
    	label1.setVisible(false);
    	label2.setVisible(false);
    	buttonSim.setVisible(false);
    	buttonSim.setVisible(false);
    	erroDeletarAutor.setText("Autor excluído com sucesso.");
    	erroDeletarAutor.setVisible(true);
    	botao.setVisible(true);
    	try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    private void voltar(ActionEvent event) {
	    Stage stage = (Stage) erroDeletarAutor.getScene().getWindow();
	    stage.close();
    }
}


