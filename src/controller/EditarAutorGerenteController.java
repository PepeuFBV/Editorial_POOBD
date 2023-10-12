package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditarAutorGerenteController {

	@FXML
	private TextField cpf;
	
	@FXML
	private TextField nome;
	
	@FXML
	private TextField endereco;
	
	@FXML
	private Label erroEditarAutorGerente;
	
	@FXML
	private Button btncancelar;
	
	public void concluir(ActionEvent event) {
	    String cpfText = cpf.getText();
	    String nomeText = nome.getText();
	    String enderecoText = endereco.getText();
	    if (cpfText.isEmpty() || nomeText.isEmpty() || enderecoText.isEmpty()) {
	    	erroEditarAutorGerente.setText("Por favor, preencha todos os campos.");
	    	erroEditarAutorGerente.setVisible(true);
	    } else {
	        System.out.println("Edição bem-sucedida.");
	        erroEditarAutorGerente.setText("Edição bem-sucedida.");
	        erroEditarAutorGerente.setVisible(true);
	        btncancelar.setText("Fechar");
	    }
	}
	
	public void cancelar(ActionEvent event) {
		Stage stage = (Stage) erroEditarAutorGerente.getScene().getWindow();
	    stage.close();
	}
	
}
