package controller;

import view.Telas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class AvaliarObraController {
	
	ObservableList<String> relatorios = FXCollections.observableArrayList("Relatório 1", "Relatório 2", "Relatório 3");
	
	@FXML
	private Label erroAut;
	
	@FXML PasswordField senhaavaliarobra;
	
	@FXML
	private ChoiceBox<String> choiceboxrelatorio;
	
	@FXML
	private RadioButton buttonaceita;
	
	@FXML
	private RadioButton buttonrejeitada;
	
	@FXML
	private ToggleGroup radioavaliar;
	
	@FXML
	private void initialize() {
		choiceboxrelatorio.setItems(relatorios);
	}
	
	public void enviar(ActionEvent event) {
	    try {
	        String senha_do_avaliador = "12345";
	        String senha = senhaavaliarobra.getText();
	        
	        if (senha.equals(senha_do_avaliador)) {
	            // obras_avaliadas.relatorios = choiceboxrelatorio.getValue();
	        } else {
	            erroAut.setText("Senha incorreta.");
	            erroAut.setVisible(true);
	            return;
	        }
	        	
	        String selecao = (String) choiceboxrelatorio.getValue(); //caminho do arquivo de texto
            if (selecao != null) {
                // obra.relatorios = selecao; 
            } else {
                erroAut.setText("Você deve selecionar um relatório.");
                erroAut.setVisible(true);
                return;
            }
	        	
            if (buttonaceita.isSelected()) {
                // obra.setStatus("Aceita");
            	System.out.println("Avaliação bem-sucedida.");
            } else if (buttonrejeitada.isSelected()) {
                // obra.setStatus("Rejeitada");
            	System.out.println("Avaliação bem-sucedida.");
            } else {
                erroAut.setText("Você deve selecionar uma situação.");
                erroAut.setVisible(true);
                return;
            }
            

	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	}
	
	public void voltarTelaOBrasAvaliador(ActionEvent event) {
		try {
			Telas.telaPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
