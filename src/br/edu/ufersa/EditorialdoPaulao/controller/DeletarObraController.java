package controller;


import view.Telas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DeletarObraController {
	
	@FXML 
	private Label label1;
	
	@FXML 
	private Label label2;
	
	@FXML
	private Label label3;
	
    @FXML
    private Button buttonSim;

    @FXML
    private Button buttonNao;
    
    @FXML
    private Button botao;

    @FXML
    private void handleSimButtonAction(ActionEvent event) {
    	System.out.println("Obra excluída com sucesso.");
    	label1.setVisible(false);
    	label2.setVisible(false);
    	buttonSim.setVisible(false);
    	buttonSim.setVisible(false);
    	label3.setText("Obra excluída com sucesso.");
    	label3.setVisible(true);
    	botao.setVisible(true);
    	try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    private void handleNaoButtonAction(ActionEvent event) {
    	try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    @FXML
    private void voltar(ActionEvent event) {
    	try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}


